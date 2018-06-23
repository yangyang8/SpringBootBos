package com.hailong.bos.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hailong.bos.dao.admin.TPermissionDao;
import com.hailong.bos.domain.TPermissions;
import com.hailong.bos.domain.User;
import com.hailong.bos.domain.json.CheckBoxTreeJson;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.service.TPermissionService;
import com.hailong.bos.utils.SessionUtils;

@Service
@Transactional
public class TPermissionServiceImpl implements TPermissionService{

	//注入我们的权限Dao层数据
	@Autowired
	private TPermissionDao permissionDao;
	
	@Override
	public List<TPermissions> getAllTPermission() {
		//return permissionDao.findAll();
		List<TPermissions> list=permissionDao.findByParentPermissionIsNull();
		//CheckBoxTreeJson<TPermissions> tree=new CheckBoxTreeJson<>(id, text, children);
		return list;
	}

	//我们的权限的增加和修改方法
	@Override
	public void saveOrUpdate(TPermissions permission) {
		TPermissions tp=permission.getParentPermission();
		if(tp!=null&&tp.getId().equals("")){
			permission.setParentPermission(null);
		}
		permissionDao.save(permission);
		
	}

	//进行分页查询数据,没有分页的操作
	@Override
	public JsonUtils<TPermissions> pageListTPermission(Integer page, Integer rows) {
		
		//先进行测试分页的相关的数据
		PageRequest pageRequest=new PageRequest(page-1, rows);
		//进行分页查询数据
		Page<TPermissions> p= permissionDao.findAll(pageRequest);
		
		//进行查询总的记录数据
		long count=permissionDao.count();
		
		//进行创建JsonUtils<TPermissions>对象
		JsonUtils<TPermissions> js=new JsonUtils<TPermissions>(count,p.getContent());
		
		return js;
	}

	
	//根据当前用户的Id来查询所有的菜单数据
	@Override
	public List<TPermissions> getMenuPermissionData(HttpServletRequest request) {
		User user=SessionUtils.getLoginUser(request);
		List<TPermissions> list=null;
		if(user!=null&&user.getUsername().equals("admin")){
			//则查询所有的数据权限数据
			list=permissionDao.findByIsGenernatemenuOrderByZindexAsc("1");
		}else if(user!=null){
			//根据用户的Id来查询所有的菜单数据
			list=permissionDao.findByUserIdGenernate(user.getId(),"1");
		}
		return list;
	}

}
