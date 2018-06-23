package com.hailong.bos.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 工单的管理
 */

@Entity
@DynamicInsert
@DynamicUpdate
public class WorkManager implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="workManager",strategy="assigned")
	@GeneratedValue(generator="workManager")
	private String id;//工作单号
	
	private String arriveCity; 		//源城市
	private String product;  		//产品内容
	private Integer num;			//内容
	private Double weight;			//重量
	private String floadReqr;	 	// 配载要求
	private String prodTimeLimit; 	//产品期限
	private String prodType;		//产品类型
	private String senderName;		//发送方名字
	private String senderPhone;		//发送人电话
	private String senderAddr;		//发送人地址
	private String receiverName;	//收货人姓名
	private Integer feeitemNum;		//计费件数
	private Double actlWeit;		//实际重要
	private String vol;				//体积
	private String managerCheck;	//是否审核通过配送 
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date updateTime=new Date();		//更新时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getFloadReqr() {
		return floadReqr;
	}
	public void setFloadReqr(String floadReqr) {
		this.floadReqr = floadReqr;
	}
	public String getProdTimeLimit() {
		return prodTimeLimit;
	}
	public void setProdTimeLimit(String prodTimeLimit) {
		this.prodTimeLimit = prodTimeLimit;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderPhone() {
		return senderPhone;
	}
	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}
	public String getSenderAddr() {
		return senderAddr;
	}
	public void setSenderAddr(String senderAddr) {
		this.senderAddr = senderAddr;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public Integer getFeeitemNum() {
		return feeitemNum;
	}
	public void setFeeitemNum(Integer feeitemNum) {
		this.feeitemNum = feeitemNum;
	}
	public Double getActlWeit() {
		return actlWeit;
	}
	public void setActlWeit(Double actlWeit) {
		this.actlWeit = actlWeit;
	}
	public String getVol() {
		return vol;
	}
	public void setVol(String vol) {
		this.vol = vol;
	}
	public String getManagerCheck() {
		return managerCheck;
	}
	public void setManagerCheck(String managerCheck) {
		this.managerCheck = managerCheck;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
