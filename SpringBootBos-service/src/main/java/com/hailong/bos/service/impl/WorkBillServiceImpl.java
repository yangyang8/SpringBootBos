package com.hailong.bos.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hailong.bos.dao.WorkBillDao;
import com.hailong.bos.domain.WorkBill;
import com.hailong.bos.service.WorkBillService;

@Service
@Transactional
public class WorkBillServiceImpl implements WorkBillService{
	
	//注入WorKBillDao对象
	private WorkBillDao workBillDao;
	
	public void SavaOrUpdate(WorkBill workBill) {
		workBillDao.save(workBill);
	}

}
