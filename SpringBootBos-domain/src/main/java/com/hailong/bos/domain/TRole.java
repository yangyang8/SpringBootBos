package com.hailong.bos.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 角色表
 * @author Administrator
 *
 */

@Entity
public class TRole implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="trole",strategy="uuid")
	@GeneratedValue(generator="trole")
	private String id;
	private String name;
	private String description;
	private String code;
	//有多个用户
	//Hibernate 会自动创建一张关系表role_user， 里边有俩字段role_id和user_id分别为两表主键
	@ManyToMany(mappedBy = "roleSet")
	@NotFound(action = NotFoundAction.IGNORE)  //意思是找不到引用的外键数据时忽略，NotFound默认是exception
	private Set<User> userSet=new HashSet<User>();
	
	//有多个权限
	@ManyToMany
	@JoinTable(name="role_permission",joinColumns={@JoinColumn(name="role_id")},
	inverseJoinColumns={@JoinColumn(name="permissor_id")})
	private Set<TPermissions> permissSet=new HashSet<TPermissions>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<User> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}

	public Set<TPermissions> getPermissSet() {
		return permissSet;
	}

	public void setPermissSet(Set<TPermissions> permissSet) {
		this.permissSet = permissSet;
	}

}
