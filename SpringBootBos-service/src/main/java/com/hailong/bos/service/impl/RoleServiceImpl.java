package com.hailong.bos.service.impl;

import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hailong.bos.dao.admin.RoleDao;
import com.hailong.bos.domain.TPermissions;
import com.hailong.bos.domain.TRole;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public JsonUtils<TRole> pageRoleList(Integer page, Integer rows) {
		//先进行测试分页的相关的数据
		PageRequest pageRequest=new PageRequest(page-1, rows);
		//进行分页查询数据
		Page<TRole> p= roleDao.findAll(pageRequest);
		
		//进行查询总的记录数据
		long count=roleDao.count();
		
		//进行创建JsonUtils<TPermissions>对象
		JsonUtils<TRole> js=new JsonUtils<TRole>(count,p.getContent());
		
		return js;
	}
	
	/**
	 * 要进行关联我们的权限表当中的数据,
	 * 是多对多的关系，存在第三方的表当中，让角色表来关联权限数据
	 */
	@Override
	public void saveOrUpdate(TRole role,String permissionIds) {
		
		if(permissionIds!=null&&StringUtils.isNotBlank(permissionIds)){
			//进行分割字符串
			String permissionId[]=permissionIds.split(",");
			TPermissions tp=null;
			Set<TPermissions> set=role.getPermissSet();
			for(String id:permissionId){
				//进行创建一个Permission对象
				tp=new TPermissions();
				tp.setId(id);
				set.add(tp);//持久态的数据可以设置暂时态的数据
			}
		}
		roleDao.save(role);
	}
}
