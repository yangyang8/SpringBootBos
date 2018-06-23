package com.hailong.bos.service;

import com.hailong.bos.domain.TRole;
import com.hailong.bos.domain.json.JsonUtils;

/**
 * 我们的角色service操作
 * @author Administrator
 *
 */
public interface RoleService {
	public JsonUtils<TRole> pageRoleList(Integer page,Integer rows);
	
	//进行保存角色方法
	public void saveOrUpdate(TRole role,String permissionIds);
}
