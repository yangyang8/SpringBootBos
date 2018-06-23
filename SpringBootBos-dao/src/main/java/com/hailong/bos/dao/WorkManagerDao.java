package com.hailong.bos.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hailong.bos.domain.WorkManager;
/**
 * 工单的持久层操作如下
 * @author Administrator
 *
 */
public interface WorkManagerDao extends JpaRepository<WorkManager,Serializable> {
	
}
