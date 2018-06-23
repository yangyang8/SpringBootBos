package com.hailong.bos.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 工单操作,工单表示的是快递员的任务
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class WorkBill {
    
	@Id
	@GenericGenerator(name="workBill",strategy="uuid")
	@GeneratedValue(generator="workBill")
	private String id;  //工单号
	
	@OneToOne
	@JoinColumn(name="noticeBill_id")
	private NoticeBill noticeBill;	//业务通知单
	
	@ManyToOne
	@JoinColumn(name="staff_id")
	private BCStaff staff;			//取件人
	private String type;			//工单类型,有4种类型 新单，改单，销单，最单
	private String pickstate;		//取件状态 有三种，分别表示 未取件，取件中，已取件
	
	private Date buildtime;			//工单生成时间
	private Integer attackBillTime;	//追单时间
	private String remark;			//备注信息
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public NoticeBill getNoticeBill() {
		return noticeBill;
	}
	
	public void setNoticeBill(NoticeBill noticeBill) {
		this.noticeBill = noticeBill;
	}
	
	public BCStaff getStaff() {
		return staff;
	}
	
	public void setStaff(BCStaff staff) {
		this.staff = staff;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getPickstate() {
		return pickstate;
	}
	
	public void setPickstate(String pickstate) {
		this.pickstate = pickstate;
	}
	
	public Date getBuildtime() {
		return buildtime;
	}
	
	public void setBuildtime(Date buildtime) {
		this.buildtime = buildtime;
	}
	
	public Integer getAttackBillTime() {
		return attackBillTime;
	}
	
	public void setAttackBillTime(Integer attackBillTime) {
		this.attackBillTime = attackBillTime;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
