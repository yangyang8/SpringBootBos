package com.hailong.bos.dao.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hailong.bos.domain.User;

@Repository  //表示这个类是一个仓库
public interface UserDao extends JpaRepository<User,String> {
	
	@Query("select u from User u where u.username=? and u.password=?")
	public List<User> findByPasswordAndName(String username,String password);

	@Transactional
	@Modifying  //在spring 当中操作我们的hibernate当中使用sql修改sql当中的值，要加上这个Modifying注解，不然会报一个不支持DML的操作异常，
	//同时执行一个update/delete的语句还要加上事务注解，不会也会报一个必须事务异常
	@Query("update User set password=? where id=?")
	public void updateByCondition(String password,String id);

	public User findByUsername(String username);
}
