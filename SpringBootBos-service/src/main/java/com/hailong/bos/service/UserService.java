package com.hailong.bos.service;

import java.util.List;

import com.hailong.bos.domain.TRole;
import com.hailong.bos.domain.User;
import com.hailong.bos.domain.json.JsonUtils;

public interface UserService {
	
	public User login(String password,String username);

	public void updatePasswordById(String id, String password);

	public List<TRole> getAllRoles();

	public void saveOrUpdate(User user, String[] roleId);

	//分页查询方法操作
	public JsonUtils<User> pageUserList(Integer page, Integer rows);

}
