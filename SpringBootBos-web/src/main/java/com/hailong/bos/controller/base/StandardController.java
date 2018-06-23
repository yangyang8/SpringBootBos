package com.hailong.bos.controller.base;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hailong.bos.config.StringToDateConfig;
import com.hailong.bos.domain.BCStandard;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.service.StandardService;
import com.hailong.bos.utils.SessionUtils;

/**
 * 派送的标准操作
 * @author Administrator
 *
 */

@RestController 
@RequestMapping("/standard")
public class StandardController extends StringToDateConfig {
	
	@Autowired
	private StandardService standardService;
	
	@RequestMapping("/standard_save.action")
	public ModelAndView standard_save(BCStandard standard,HttpServletRequest request){
		standard.setUser(SessionUtils.getLoginUser(request));
		standardService.saveStandard(standard);
		return new ModelAndView("base/standard");
	}
	
	//查询所有
	@RequestMapping("/list.action")
	public void list(Integer page,Integer rows,HttpServletResponse response){
		JsonUtils json=standardService.findList(page,rows);
		//String js=JSON.toJSONString(json,true);
		String js=JSON.toJSONString(json,SerializerFeature.DisableCircularReferenceDetect);//忽视循环引用
		try {
			response.getWriter().print(js);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//修改数据standard /standard_edit.action
	@RequestMapping("/standard_edit.action")
	public ModelAndView standard_edit(BCStandard standard){
		standardService.update(standard);
		return new ModelAndView("base/standard");
	}
	
	//进行删除数据
	@RequestMapping("/standard_delete.action")
	public ModelAndView standard_delete(String id){
		standardService.deleteByLogic(id);
		return new ModelAndView("/base/standard");
	}
	
	

}
