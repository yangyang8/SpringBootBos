package com.hailong.bos.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hailong.bos.domain.BCDecidedzone;

@Repository
public interface DecidedzoneDao extends JpaRepository<BCDecidedzone,Serializable> {

}
