package com.hailong.bos.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 我们的权限表的操作
 * @author Administrator
 *
 */

@Entity
@DynamicInsert
@DynamicUpdate
public class TPermissions implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="tPermissions",strategy="uuid")
	@GeneratedValue(generator="tPermissions")
	private String id;
	//上级权限  ，这是因为要进行显示成一个树的形状
	@OneToOne
	@JoinColumn(name="pid")
	private TPermissions parentPermission;
	
	private String name;
	
	private String code;
	private String description;
	private String page;
	private String isGenernatemenu; //是否生成菜单 1表示是 0表示否
	
	private Integer zindex; //? 优先级
	//是一个权限和角色是一个多对多的关系操作
	
	@ManyToMany(mappedBy="permissSet")
	private Set<TRole> roles=new HashSet<TRole>();
	
	//下级权限操作
	//@OneToMany(fetch=FetchType.EAGER) //立即加载操作
	@OneToMany(fetch=FetchType.LAZY) 
	@JoinColumn(name="pid")
	private Set<TPermissions> children=new HashSet<TPermissions>();
	
	public String getText(){
		return name;
	}

	

	public Set<TPermissions> getChildren() {
		return children;
	}


	public void setChildren(Set<TPermissions> children) {
		this.children = children;
	}

	public String getpId(){
		//如果pid==null,那么则转不了json数据
		if(parentPermission==null||"".equals(parentPermission.getId())){
			return "0";
		}else{
			return parentPermission.getId();
		} 
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TPermissions getParentPermission() {
		return parentPermission;
	}

	public void setParentPermission(TPermissions parentPermission) {
		this.parentPermission = parentPermission;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getIsGenernatemenu() {
		return isGenernatemenu;
	}

	public void setIsGenernatemenu(String isGenernatemenu) {
		this.isGenernatemenu = isGenernatemenu;
	}

	public Integer getZindex() {
		return zindex;
	}

	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}

	public Set<TRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<TRole> roles) {
		this.roles = roles;
	}
}
