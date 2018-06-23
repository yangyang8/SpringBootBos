package com.hailong.bos.controller.base;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.hailong.bos.domain.BCRegion;
import com.hailong.bos.domain.BCSubArea;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.domain.stat.StatSubarea;
import com.hailong.bos.service.SubAreaService;
import com.hailong.bos.utils.FileUtils;

/**
 * 分区控件器的相关的操作
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/subarea")
public class SubAreaController {
	
	@Autowired
	private SubAreaService subareaService;
	
	/**
	 * 进行分页查询操作
	 */
	@RequestMapping("/list.action")
	public void pageList(Integer page,Integer rows,BCSubArea subarea,HttpServletResponse response){
		//得到分页的数据
	    JsonUtils<BCSubArea> json=subareaService.pageList(page, rows,subarea);
		
		
		//System.out.println(subarea);
		//进行转换操作
		//String j=JSON.toJSONString(json,SerializerFeature.DisableCircularReferenceDetect);
	    SimplePropertyPreFilter filter=new	SimplePropertyPreFilter(BCSubArea.class,new String[]{"id","region","addresskey","startnum","endnum","single","position"});
		String j=JSON.toJSONString(json,filter);
		try {
			response.getWriter().print(j);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/saveOrUpdateSubarea.action")
	public ModelAndView saveOrUpdateSubarea(BCSubArea subarea){
		if(subarea!=null){
			subareaService.saveOrUpdate(subarea);
		}
		return new ModelAndView("/base/subarea");
	}
	
	//进行查询区域信息
	@RequestMapping("/getRegions.action")
	public String getRegions(String q){
		
		List<BCRegion> js=subareaService.getAllRegions(q);
		//把List转成json
		String s=JSONArray.toJSONString(js,SerializerFeature.DisableCircularReferenceDetect);
		
		return s;
	}
	
	//进行批量的删除操作
	
	
	//进行设置导出操作
	@RequestMapping("/exportSubarea.action")
	public void exportSubarea(HttpServletRequest request,HttpServletResponse response){
		//进行查询所有分区当中的数据
		List<BCSubArea> list=subareaService.findAll();
		//进行使用POI来解析这些数据
		//创建XSSFWorkbook
		XSSFWorkbook work=new XSSFWorkbook();
		//进行创建sheet
		Sheet sheet=work.createSheet();
		//创建row
		Row row=sheet.createRow(0);
		//创建一行标题行操作
		row.createCell(0).setCellValue("分区编号");
		row.createCell(1).setCellValue("开始编号");
		row.createCell(2).setCellValue("结束编号");
		row.createCell(3).setCellValue("位置信息");
		row.createCell(4).setCellValue("省市区");
		for(BCSubArea area:list){
			//得到行号
			int rowNum=sheet.getLastRowNum();
			//创建行
			Row r=sheet.createRow(rowNum+1);
			r.createCell(0).setCellValue(area.getId());
			r.createCell(1).setCellValue(area.getStartnum());
			r.createCell(2).setCellValue(area.getEndnum());
			r.createCell(3).setCellValue(area.getPosition());
			r.createCell(4).setCellValue(area.getRegion().getName());
		}
		
		//通过POI来把数据写出Excel文件当中
		//得到一个数据流
		ServletOutputStream out;
		try {
			out = response.getOutputStream();
			//进行下载流的二个设置头
			String fileName="区域.xlsx";
			String agent=request.getHeader("User-Agent");
			fileName=FileUtils.encodeDownloadFilename(fileName,agent);
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("content-disposition", "attachment;filename="+fileName);
			work.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//统计区域的分布操作
	@RequestMapping("/statSubareaGroupByProvice.action")
	public ModelAndView statSubareaGroupByProvice(Model model){
		return new ModelAndView("stat/statSubarea","",model);
	}
	
	//统计区域的分布操作
	@RequestMapping("/getSubareaData.action")
	public String getSubareaData(){
		//查询数据库，然后得到数据，返回给前台
		List<StatSubarea> list=subareaService.getStatSubareaGroupByProvice();

		String js=JSONArray.toJSONString(list);
		return js;
	}
}
