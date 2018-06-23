package com.hailong.bos.domain.zm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.hailong.bos.domain.WorkManager;

/**
 * 
 * 中转流程 完整信息
 * 
 * @author seawind
 * 
 */

@Entity
@DynamicInsert
@DynamicUpdate
public class ZhongZhuanInfo {
	@Id
	@GenericGenerator(name="zhongZhuanInfo",strategy="uuid")
	@GeneratedValue(generator="zhongZhuanInfo")
	private Long id; // 编号 uuid
	
	
	//private Task currentTask; // 当前任务（不存储）

	// 这里为什么用List ，因为要保证中转信息顺利
	@JoinColumn(name="zhongZhuanInfoId")
	@OneToMany
	private List<TransferInfo> transferInfos = new ArrayList<TransferInfo>(); // 多次中转信息

	private String arrive; // 是否到达
	
	@JoinColumn(name="inStoreId")
	@OneToOne
	private InStore inStore; // 入库信息
	@JoinColumn(name="outStoreId")
	@OneToOne
	private OutStore outStore; // 出库信息
	
	@JoinColumn(name="receiveGoodsInfoId")
	@OneToOne
	private ReceiveGoodsInfo receiveGoodsInfo; // 配送信息
	
	@JoinColumn(name="workOrderManageId")
	@OneToOne
	private WorkManager workOrderManage; // 工作单信息

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

/*	public Task getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}*/

	public List<TransferInfo> getTransferInfos() {
		return transferInfos;
	}

	public void setTransferInfos(List<TransferInfo> transferInfos) {
		this.transferInfos = transferInfos;
	}

	public String getArrive() {
		return arrive;
	}

	public void setArrive(String arrive) {
		this.arrive = arrive;
	}

	public InStore getInStore() {
		return inStore;
	}

	public void setInStore(InStore inStore) {
		this.inStore = inStore;
	}

	public OutStore getOutStore() {
		return outStore;
	}

	public void setOutStore(OutStore outStore) {
		this.outStore = outStore;
	}

	public ReceiveGoodsInfo getReceiveGoodsInfo() {
		return receiveGoodsInfo;
	}

	public void setReceiveGoodsInfo(ReceiveGoodsInfo receiveGoodsInfo) {
		this.receiveGoodsInfo = receiveGoodsInfo;
	}

	public WorkManager getWorkOrderManage() {
		return workOrderManage;
	}

	public void setWorkOrderManage(WorkManager workOrderManage) {
		this.workOrderManage = workOrderManage;
	}

	@Override
	public String toString() {
		return "中转流程信息 [中转环节=" + transferInfos + ", 是否到达=" + arrive + ", 入库信息=" + inStore + ", 出库信息=" + outStore + ", 签收信息=" + receiveGoodsInfo + ", 工作单信息=" + workOrderManage + "]";
	}

}
