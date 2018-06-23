package com.hailong.bos.dao.admin;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hailong.bos.domain.TRole;

@Repository
public interface RoleDao extends JpaRepository<TRole,Serializable> {

}
