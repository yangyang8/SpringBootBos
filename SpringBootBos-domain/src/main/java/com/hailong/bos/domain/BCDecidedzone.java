package com.hailong.bos.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 基础设置当中的 定区表，是快递分配的基本单位
 * @author Administrator
 *
 */


@Entity
@DynamicInsert
@DynamicUpdate
public class BCDecidedzone {
	
	@Id
	@GeneratedValue(generator="BCDecidedzone")		 
	@GenericGenerator(name="BCDecidedzone",strategy="assigned")
	private String id; //定区编码
	private String username; //定区名称
	//private String staffId;  //定区负责人  那个快递员负责那个定区，一个快递员可以负责多个定区
	@JoinColumn(name="staff_id")//指定自己的ID
	@ManyToOne  //表示关联关系为一对一
	private BCStaff staff;  //一个定区只能是邮一个快递员负责
	
	@JoinColumn(name="decidedzone_id") //多对一，则要指定自己的Id
	@OneToMany
	private Set<BCSubArea> setSubarea=new HashSet<BCSubArea>();
	
	public Set<BCSubArea> getSetSubarea() {
		return setSubarea;
	}
	public void setSetSubarea(Set<BCSubArea> setSubarea) {
		this.setSubarea = setSubarea;
	}
	
	public String getId() {
		return id;
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
	
	public BCStaff getStaff() {
		return staff;
	}
	
	public void setStaff(BCStaff staff) {
		this.staff = staff;
	}
}
