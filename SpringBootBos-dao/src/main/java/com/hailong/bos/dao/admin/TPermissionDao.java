package com.hailong.bos.dao.admin;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hailong.bos.domain.TPermissions;


/**
 * 权限的Dao层接口操作
 * @author Administrator
 *
 */
@Repository
public interface TPermissionDao extends JpaRepository<TPermissions,Serializable> {
	
	//进行查询顶级权限
	public List<TPermissions> findByParentPermissionIsNull();

	//根据用户的Id来查询当前用户的所有权限数据,用户跟角色关联，角色和用户关联
	//有问题@Query("SELECT p FROM TPermissions p LEFT JOIN TROLE r ON p.permissor_id=r.role_id LEFT JOIN User u ON u.user_id=r.role_id WHERE u.user_id=:id")
	@Query("SELECT DISTINCT p FROM TPermissions p LEFT JOIN p.roles r LEFT JOIN r.userSet u WHERE u.id=?")
	public List<TPermissions> findByTpermissoinAndUserId(String id);
	
	//
	public List<TPermissions> findByIsGenernatemenuOrderByZindexAsc(String isGenernatemenu);
	
	//根据用户的Id来联合查询用户的权限数据
	@Query("SELECT DISTINCT p FROM TPermissions p LEFT JOIN p.roles r LEFT JOIN r.userSet u WHERE u.id=? AND p.isGenernatemenu=? ORDER BY p.zindex ASC")
	public List<TPermissions> findByUserIdGenernate(String id,String isGenernatemenu);

}
