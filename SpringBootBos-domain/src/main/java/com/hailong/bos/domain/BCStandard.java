package com.hailong.bos.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 收派标准表的操作
 * @author Administrator
 *
 */

@Entity
@DynamicUpdate(true)	//动态更新，只更新新修改的数据
@DynamicInsert(true)  //动态插入，只插入新插入的数据
public class BCStandard implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	//收派标准Id
	@Id
	@GeneratedValue(generator="standard")
	@GenericGenerator(name="standard",strategy="uuid")
	private String id;  //主键
	private String name; //标准名称
	private Double maxWeight;  //最大重要
	private Double minWeight;  //最小重要
	private String deltag="0";     //删除标准， 0表示不删除，1表示删除，在企业的数据开发当中，并不会真的删除数据，而是确定一个标志位进行逻辑删除数据
	/*private String opUnit;*/     //操作单位，可以从我们的用户表间距查出来
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date opDate;       //操作时间
	
	//谁制定的规则，一对一
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonBackReference
	private User user;       //操作的操作，也就是当中登录的用户
	
	
	/*//那么 一对多
	@JoinColumn(name="standard_id")
	@OneToMany
	@JsonBackReference
	private Set<BCStaff> staffs=new HashSet<BCStaff>();  //收派标准
*/	
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
	public Double getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(Double maxWeight) {
		this.maxWeight = maxWeight;
	}
	public Double getMinWeight() {
		return minWeight;
	}
	public void setMinWeight(Double minWeight) {
		this.minWeight = minWeight;
	}
	public String getDeltag() {
		return deltag;
	}
	public void setDeltag(String deltag) {
		this.deltag = deltag;
	}
	public Date getOpDate() {
		return opDate;
	}
	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	/*public Set<BCStaff> getStaffs() {
		return staffs;
	}
	public void setStaffs(Set<BCStaff> staffs) {
		this.staffs = staffs;
	}*/
/*	@Override
	public String toString() {
		return "BCStandard [id=" + id + ", name=" + name + ", maxWeight=" + maxWeight + ", minWeight=" + minWeight
				+ ", deltag=" + deltag + ", opDate=" + opDate + ", user=" + user + ", staffs=" + staffs + "]";
	}*/
	
	
}
