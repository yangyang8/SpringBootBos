package com.hailong.bos.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hailong.bos.domain.BCStandard;

@Repository
public interface StandardDao extends JpaRepository<BCStandard,String>{
	
	//增加方法
	
	//删除方法
	
	//查询所有
	
	//查询单个
	public List<BCStandard> findByDeltag(String deltag);
	@Query("select id,name from BCStandard where deltag=?")
	public List<BCStandard> findByDeltags(String deltag);
	//修
	
	//分页查询
	public Page<BCStandard> findByDeltag(String name,Pageable pageable);
	//进行批量的修改数据数据
	@Transactional
	@Modifying
	@Query("update BCStandard SET deltag=:deltag where id in(:id)")
	public void updateBatchById(@Param("deltag") String deltag,@Param("id") List<String> id);

}
