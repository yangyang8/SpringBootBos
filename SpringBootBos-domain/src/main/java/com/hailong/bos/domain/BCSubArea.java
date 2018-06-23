package com.hailong.bos.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 基础设置分区表 ，一个街道，一个小区可以分成一个区 
 * 
 * @author Administrator
 *
 */
@Table(name="bcsubarea")
@Entity
@DynamicInsert
@DynamicUpdate
public class BCSubArea implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="BCSubArea")
	@GenericGenerator(name="BCSubArea",strategy="uuid")
	private String id; //城市编号
	//private String decidedzoneId; //定区主键
	//private String regionId; //区域设置主键
	@JoinColumn(name = "decidedzone_id")//user表中使用department_id字段来表示部门id
    @JsonBackReference//防止关系对象的递归访问
	@OneToOne
	private BCDecidedzone decidedzone; //一个分区当中只能归属于定区 【一对多关系】
	@JoinColumn(name = "region_id")//user表中使用department_id字段来表示部门id，这样子就不会再自动创建多一个表来存他们的关系了
	@OneToOne(fetch=FetchType.EAGER)
	//@OneToOne(fetch=FetchType.LAZY)
	private BCRegion region;  //一个分区当中只能归属于一个区域  【一对多关系】

	private String addresskey; //地址关键字
	private String startnum;   //起始号
	private String endnum;  //终止号
	private String single;  //单双号
	private String position;  //位置信息
	
	public String getSubareaId(){
		return id;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public BCDecidedzone getDecidedzone() {
		return decidedzone;
	}
	public void setDecidedzone(BCDecidedzone decidedzone) {
		this.decidedzone = decidedzone;
	}
	public BCRegion getRegion() {
		return region;
	}
	public void setRegion(BCRegion region) {
		this.region = region;
	}
	public String getAddresskey() {
		return addresskey;
	}
	public void setAddresskey(String addresskey) {
		this.addresskey = addresskey;
	}
	public String getStartnum() {
		return startnum;
	}
	public void setStartnum(String startnum) {
		this.startnum = startnum;
	}
	public String getEndnum() {
		return endnum;
	}
	public void setEndnum(String endnum) {
		this.endnum = endnum;
	}
	public String getSingle() {
		return single;
	}
	public void setSingle(String single) {
		this.single = single;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}