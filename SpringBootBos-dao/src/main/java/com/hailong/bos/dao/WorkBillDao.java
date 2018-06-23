package com.hailong.bos.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hailong.bos.domain.WorkBill;

public interface WorkBillDao extends JpaRepository<WorkBill,Serializable>{

}
