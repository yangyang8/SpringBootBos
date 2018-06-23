package com.hailong.bos.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hailong.bos.domain.NoticeBill;

public interface NoticeBillDao extends JpaRepository<NoticeBill, Serializable> {

}
