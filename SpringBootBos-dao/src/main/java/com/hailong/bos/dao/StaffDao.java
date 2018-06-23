package com.hailong.bos.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hailong.bos.domain.BCStaff;

public interface StaffDao extends JpaRepository<BCStaff,Long> {
	
	//进行自定义一个分页操作的方法
	/**
	 * 会自动的去扫描我们的这个BCStaff对象的属性，看BCStaff对象当中有没有我们的findByXXXXyyy中的XXXX
	 * ，如果没有则会报错没有XXXX属性错误
	 * @param username
	 * @param pageable
	 * @return
	 */
	public Page<BCStaff> findByUsernameNot(String username,Pageable pageable);
	
	//根据id查询数据
	public BCStaff findById(String id);
	
	//逻辑删除快递员数据
	@Modifying(flushAutomatically=true)
	@Transactional
	@Query("update BCStaff set deltag=:deltag where id in (:id)")  //修改操作只能用Int/Interger或void作为返回值 modifying queries can only use void or int/Integer as return type
	public void updateDeltagById(@Param("deltag") String deltag,@Param("id") List<String> id);
	
	
	//查询所有没有被删除的数据
	public List<BCStaff> findByDeltag(String deltag);
	
}
