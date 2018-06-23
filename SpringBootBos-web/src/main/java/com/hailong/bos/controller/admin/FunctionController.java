package com.hailong.bos.controller.admin;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.hailong.bos.domain.TPermissions;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.service.TPermissionService;

/**
 * 权限的控制器操作
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/permission")
public class FunctionController {
	
	//注入权限服务对象
	@Autowired
	public TPermissionService permissionService;
	
	/**
	 * 控制跳转到我们的增加权限页面
	 * @return
	 */
	@RequestMapping("/returnAddPermission.action")
	public ModelAndView addPermission(){
		return new ModelAndView("admin/function_add");
	}
	
	/**
	 * 保存权限数据到数据库的相关的权限操作
	 * @return
	 */
	@RequestMapping("/saveOrUpdatePermission.action")
	public ModelAndView savePermisson(TPermissions permission){
		permissionService.saveOrUpdate(permission);
		return new ModelAndView("admin/function");
	}
	
	//得到所有的权限数据
	@RequestMapping("/ajaxAllTPermission.action")
	public String getAjaxAllTPermission(){
		List<TPermissions> list=permissionService.getAllTPermission();
		//创建json过滤器
		SimplePropertyPreFilter filter=
				new SimplePropertyPreFilter(TPermissions.class,new String[]{"id","name","text","children"});
		//转成json数据
		String json=JSONArray.toJSONString(list,filter);
		
		//进行写回数据当中
		return json;
	}
	
	//分页查询的权限数据的操作
	@RequestMapping("/pageList.action")
	public String pageTPermissionList(Integer page,Integer rows){
		JsonUtils<TPermissions> json=permissionService.pageListTPermission(page,rows);
		SimplePropertyPreFilter filter=new SimplePropertyPreFilter(TPermissions.class,
				new String[]{"id","name","code","description","page","isGenernatemenu","zindex"});
		//进行转化成json数据
		String js=JSON.toJSONString(json,filter);
		//返回数据给前端展示操作
		return js;
	}
	
	//得到菜单数据权限
	@RequestMapping("/getMenuPermissionData.action")
	public String getMenuPermissionData(HttpServletRequest request){
		List<TPermissions> permissions=permissionService.getMenuPermissionData(request);
		SimplePropertyPreFilter filter=new SimplePropertyPreFilter(TPermissions.class,
				new String[]{"id","name","pId","page"}
				);
		String json=JSONArray.toJSONString(permissions,filter);
		return json;
	}
}
