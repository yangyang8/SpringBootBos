package com.hailong.bos.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.hailong.bos.domain.TRole;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	//跳转到增加页面
	@RequestMapping("/roleAddPage.action")
	public ModelAndView roleAddPage(){
		return new ModelAndView("admin/role_add");
	}
	
	//跳转到修改页面
	@RequestMapping("/roleEditPage")
	public ModelAndView roleEditPage(){
		return new ModelAndView("admin/role_edit");
	}
	
	/**
	 * 跳转删除页面
	 * TODO
	 */
	
	//分页查询数据
	@RequestMapping("/pageRoleList.action")
	public String pageRoleList(Integer page,Integer rows){
		JsonUtils<TRole> json=roleService.pageRoleList(page, rows);
		SimplePropertyPreFilter filter=new SimplePropertyPreFilter(TRole.class,new String[]{"id","name","description","code"});
		String js=JSON.toJSONString(json,filter);
		return js;
	}
	
	//保存数据
	@RequestMapping("/saveOrUpdateRole.action")
	public ModelAndView saveOrUpdateRole(TRole role,String permissionIds){
		roleService.saveOrUpdate(role,permissionIds);
		return new ModelAndView("admin/role");
	}
	
	
	
}
