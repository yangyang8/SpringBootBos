package com.hailong.bos.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 通知单操作表示
 * @author Administrator
 */

@Entity
@DynamicInsert
@DynamicUpdate
public class NoticeBill {
	
	@Id
	@GenericGenerator(name="noticeBill",strategy="uuid")
	@GeneratedValue(generator="noticeBill")
	private String id;
	private String telepone;
	private String pickaddress;
	private String arriveCity;
	private String product;
	private Date pickData;
	private Integer num;
	private Double weight;
	private String volume;
	private String remark;
	private String orderType;
	@OneToOne
	@JoinColumn(name="user_id")
	private User users;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTelepone() {
		return telepone;
	}
	public void setTelepone(String telepone) {
		this.telepone = telepone;
	}
	public String getPickaddress() {
		return pickaddress;
	}
	public void setPickaddress(String pickaddress) {
		this.pickaddress = pickaddress;
	}
	public String getArriveCity() {
		return arriveCity;
	}
	public void setArriveCity(String arriveCity) {
		this.arriveCity = arriveCity;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public Date getPickData() {
		return pickData;
	}
	public void setPickData(Date pickData) {
		this.pickData = pickData;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
}
