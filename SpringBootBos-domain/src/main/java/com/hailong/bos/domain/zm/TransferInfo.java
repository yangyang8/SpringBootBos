package com.hailong.bos.domain.zm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 中转环节信息
 * 
 * @author seawind
 * 
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class TransferInfo implements Serializable {
	
	@Id
	@GenericGenerator(name="TransferInfo",strategy="uuid")
	@GeneratedValue(generator="TransferInfo")
	private Long id; // 自动生成 id
	private String info; // 中转信息
	private String arrive; // 是否到达  0 未到达， 1 已经到达 

	private Date updateTime; // 更新时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getArrive() {
		return arrive;
	}

	public void setArrive(String arrive) {
		this.arrive = arrive;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "TransferInfo [info=" + info + "]";
	}

}
