package com.hailong.bos.service;

import com.hailong.bos.domain.NoticeBill;

public interface NoticeBillService {
	
	//进行自动分单的基本的操作
	public Object CheckCustomerTelephone(String telephone);
	
	//进行自动分单操作
	public void autoDivierOrder(NoticeBill noticeBill);
	
	
}
