package com.hailong.bos.domain.zm;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 配送签收
 * @author seawind
 *
 */

@Entity
@DynamicInsert
@DynamicUpdate
public class ReceiveGoodsInfo {
	
	@Id
	@GenericGenerator(name="receiveGoodsInfo",strategy="uuid")
	@GeneratedValue(generator="receiveGoodsInfo")
	private Long id;
	private String info;
	private Date updateTime;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
