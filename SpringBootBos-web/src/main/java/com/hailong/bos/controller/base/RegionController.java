package com.hailong.bos.controller.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.hailong.bos.domain.BCRegion;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.service.RegionServer;
import com.hailong.bos.utils.PinYin4jUtils;

/**
 * 区域列表的操作控制器
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/region")
public class RegionController {
	
	//进行注入regionService
	@Autowired
	private RegionServer regionService;
	
	/**
	 * 
	 * @param page  页号
	 * @param rows  页的大小
	 * @param response  响应
	 */
	@RequestMapping("/pageList.action")
	public void list(Integer page,Integer rows,HttpServletResponse response){
		JsonUtils<BCRegion> json=regionService.pageList(page,rows);
		//进行转成json对象
		//String js=JSON.toJSONString(json,SerializerFeature.DisableCircularReferenceDetect);
		/*SerializeConfig config=new SerializeConfig();

		config.addFilter(this.getClass(), SerializeFilter);*/
		//SimplePropertyPreFilter filter=new SimplePropertyPreFilter("name");
		//String js=JSON.toJSONString(json,filter);//SerializerFeature.DisableCircularReferenceDetect以后这个可以完全不用写了
		String js=JSON.toJSONString(json);
		try {
			response.getWriter().print(js);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/loadRegionInfoFile.action")
	public ModelAndView loadRegionInfoFile(@RequestParam("loadRegionFile") MultipartFile loadRegionFile) throws Exception{
		//System.out.println(loadRegionFile.getName());//loadRegionFile
		//loadRegionFile.getOriginalFilename();//区域导入测试数据.xls (系统找不到指定的文件。)
		//使用我们的POI来解析这个文件，然后导入到数据库当中,我们使用的是EXECL2007以上的基本的操作
		//loadRegionFile.getInputStream();
		List<BCRegion> list=new ArrayList<BCRegion>();
				
		XSSFWorkbook xssf=new XSSFWorkbook(loadRegionFile.getInputStream());//区域导入测试数据.xls (系统找不到指定的文件。)
		
		//得到他的Sheet
		Sheet sheet=xssf.getSheetAt(0);
		
		//得到这个Sheet当中的所有的行
		for(Row row:sheet){
			if(row.getRowNum()==0){
				continue; //不解析标题行
			}else{
				//得到行当中的所有的单元格
				//城市编号
				String id=row.getCell(0).getStringCellValue();
				//省分
				String province=row.getCell(1).getStringCellValue();
				//城市
				String city=row.getCell(2).getStringCellValue();
				//区(县)域
				String district=row.getCell(3).getStringCellValue();
				//邮编
				String postcode=row.getCell(4).getStringCellValue();
				//简码，也也就是把区域信息进行了缩写(一般就城市的拼音的简写)
				//String shortcode=row.getCell(5).getStringCellValue();
				//String citycode=row.getCell(6).getStringCellValue();
				//进行分别去除最后一个字符串操作
			    String c=city.substring(0,city.length()-1);
			    String p=province.substring(0,province.length()-1);
			    String d=district.substring(0,district.length()-1);
			    
			    //进行拼接成一个字符串
			    String info=p+""+c+""+d;
			    //得到他们的简码，首字母大写，且只取每个字的第一个字母
			    String[] shortCodes=PinYin4jUtils.getHeadByString(info);
			    String shortcode=StringUtils.join(shortCodes);
			    //System.out.println(shortCode);
			    
			    //进行得到城市的编码操作
			    String cityCode=PinYin4jUtils.hanziToPinyin(c,"");
			  //  System.out.println(cityCode);
				
				
				//创建一个对象Region对象
				BCRegion bcr=new BCRegion(id, province, city, district, postcode, shortcode,cityCode,null);
				list.add(bcr);
			}
		}
		regionService.saveBatch(list);
		return new ModelAndView("/base/region");
	}
	
	
	//进行实现区域数据的录入功能
	@RequestMapping("/saveOrUpdateRegion.action")
	public ModelAndView saveRegion(BCRegion region){
		
		regionService.saveOrUpdate(region);
		return new ModelAndView("/base/region");
	}
	
	//批量删除数据		  
	@RequestMapping("/deleteRegion.action")
	public ModelAndView deleteRegion(String id){
		regionService.deleteById(id);
		return new ModelAndView("/base/region");
	}

}
