package com.hailong.bos.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 基础设置 快递员工表
 * @author Administrator
 *
 */

@Entity
@Table(name="bcstaff")
@DynamicUpdate(true)	//动态更新，只更新新修改的数据
@DynamicInsert(true)  //动态插入，只插入新插入的数据
public class BCStaff{

	
	@Id
	@GeneratedValue(generator="staff")
	@GenericGenerator(name="staff",strategy="uuid")
	private String id; //编号
	private String username; //快递员姓名
	private String telephone; //快递员电话
	private String haspda="0"; //是否有PDA 1表示有，0表示没有，如果在前端没有点那个复选框的话，那么发送过来的数据是没有这个字段的
	private String deltag="0";   //作废标记 是用来逻辑删除快递员，不是真的删除  0表示正常使用,1表示删除
	private String station;  //所属单位
	//是一对多的方式
	@JoinColumn(name="standard_id")
	@ManyToOne
	@JsonBackReference
	private BCStandard standard;  //收派标准
	
	/*//一个快递员可以负责多个定区
	//@JsonIgnore
	@JoinColumn(name="staff_id")
    @JsonBackReference//防止关系对象的递归访问
	@OneToMany  //表示表的关联关系
	private Set<BCDecidedzone> decidedzones=new HashSet<BCDecidedzone>();
	
	public Set<BCDecidedzone> getDecidedzones() {
		return decidedzones;
	}
	public void setDecidedzones(Set<BCDecidedzone> decidedzones) {
		this.decidedzones = decidedzones;
	}*/
	
	public String getId() {
		return id;
	}
	public BCStandard getStandard() {
		return standard;
	}
	public void setStandard(BCStandard standard) {
		this.standard = standard;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getHaspda() {
		return haspda;
	}
	public void setHaspda(String haspda) {
		this.haspda = haspda;
	}
	public String getDeltag() {
		return deltag;
	}
	public void setDeltag(String deltag) {
		this.deltag = deltag;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}

    
/*    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//保证存取时有正确的格式
   // private Date createDate;
    @ManyToOne
    @JoinColumn(name = "department_id")//user表中使用department_id字段来表示部门id
    @JsonBackReference//防止关系对象的递归访问
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "ID")})
    //中间表user_role来存在各自的id,以表示它们的对应关系
    private List<Role> roles;*/
}
