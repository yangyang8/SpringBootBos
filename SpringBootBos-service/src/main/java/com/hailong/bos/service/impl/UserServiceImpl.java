package com.hailong.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hailong.bos.dao.admin.RoleDao;
import com.hailong.bos.dao.user.UserDao;
import com.hailong.bos.domain.TRole;
import com.hailong.bos.domain.User;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.service.UserService;
import com.hailong.bos.utils.MD5Utils;

@Service
public class UserServiceImpl implements UserService {
	
	//注入Dao层的基本的操作
	@Autowired
	private UserDao userDao;
	
	//注入角色数据
	@Autowired
	private RoleDao roleDao;

	public User login(String username,String password) {
		//进行使用MD5加密我们的密码
		password=MD5Utils.md5(password);
		List<User> user=userDao.findByPasswordAndName(username,password);
		if(user!=null&&user.size()>0){
			return user.get(0);
		}
		return null;
	}

	/**
	 * 根据我们的用户的Id来修改用户的密码数据
	 */
	@Override
	public void updatePasswordById(String id, String password) {
		//进行把密码加密
		password=MD5Utils.md5(password);
		userDao.updateByCondition(password,id);
	}

	@Override
	public List<TRole> getAllRoles() {
		return roleDao.findAll();
	}

	//增加或更新方法
	@Override
	public void saveOrUpdate(User user, String[] roleId) {
		String pass=MD5Utils.md5(user.getPassword());
		user.setPassword(pass);
		if(roleId!=null&&roleId.length>0){
			TRole role=null;
			for(String id:roleId){
				//手动创建一个托管对象
				role=new TRole();
				role.setId(id);
				//使用用户对象来关联角色对象
				user.getRoleSet().add(role);
			}
		}
		userDao.save(user);
	}

	@Override
	public JsonUtils<User> pageUserList(Integer page, Integer rows) {
		
		//先进行测试分页的相关的数据
		PageRequest pageRequest=new PageRequest(page-1, rows);
		//进行分页查询数据
		Page<User> p= userDao.findAll(pageRequest);
		
		//进行查询总的记录数据
		long count=userDao.count();
		
		//进行创建JsonUtils<TPermissions>对象
		JsonUtils<User> js=new JsonUtils<User>(count,p.getContent());
		
		return js;
	}

}
