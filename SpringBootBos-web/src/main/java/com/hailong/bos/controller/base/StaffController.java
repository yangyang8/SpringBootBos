package com.hailong.bos.controller.base;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hailong.bos.domain.BCStaff;
import com.hailong.bos.domain.BCStandard;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.service.StaffService;

@RestController
@RequestMapping("/staff")
public class StaffController {
	
	//注入Staff的service
	@Autowired
	private StaffService staffService;
	
	
	//查询所有快递员的数据,是根据分页来查询数据
	@RequestMapping("/list.action")
	public void list(Integer page,Integer rows,HttpServletResponse response){
		//List<BCStaff> dataList=staffService.list();
		//使用分页查询操作
		JsonUtils staff=staffService.pageList(page, rows);
		//System.out.println(page+":"+rows);
		//转换成json数据格式
		//JSONArray array=JSONUtils.listToJsonArray(dataList);
		//String json="{'total':"+dataList.size()+",'rows':"+array+"}";
		//json=JSON.toJSONString(json,true);
		//StaffJson j=new StaffJson(dataList.size(),dataList);
		//String json=JSONObject.toJSON(j).toString();
		String json=JSON.toJSONString(staff,SerializerFeature.DisableCircularReferenceDetect);
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/addStaff.action")
	public ModelAndView addStaff(String sid,BCStaff staff,HttpServletResponse response){
		//进行保存数据到Staff表当中
		staffService.save(staff,sid);
		return new ModelAndView("base/staff");
	}
	
	//进行逻辑删除快递员的信息，还报错，则ajax请求发送成功
	@RequestMapping("/OperatorStaffByLogic.action")
	@RequiresPermissions("staff-delete")  //表示执行这个方法要具有staff-delete的权限
	public ModelAndView deleteStaffByLogic(String ids,String deltag){
		//System.out.println(ids);
		if(!StringUtils.isEmpty(ids)){
			staffService.operatorById(ids,deltag);
		}
		return new ModelAndView("base/staff");
	}
	
	/**
	 * 使用代码级别控制我们的权限的相关的操作
	 * @param staff
	 * @param sid
	 * @return
	 */
	@RequestMapping("/editStaff.action")
	public ModelAndView editStaff(BCStaff staff,String sid){
		Subject subject=SecurityUtils.getSubject();
		subject.checkPermission("staff-edit"); //如果当前用户有这个staff-edit权限，
		//那么则会执行下面的这些代码，如果没有则会抛出没有权限异常，那么则下面的代码就不会执行了
		
		if(staff!=null){
			staffService.update(staff,sid);
		}
		return new ModelAndView("base/staff");
	}
	
	@RequestMapping("/getStandards.action")
	public String getStandards(){
		List<BCStandard> list=staffService.getStandards();
		String actual = JSON.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect);  
		return actual;
	}

}
