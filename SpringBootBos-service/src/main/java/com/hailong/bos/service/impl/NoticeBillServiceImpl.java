package com.hailong.bos.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.cxf.endpoint.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hailong.bos.dao.DecidedzoneDao;
import com.hailong.bos.dao.NoticeBillDao;
import com.hailong.bos.dao.WorkBillDao;
import com.hailong.bos.domain.BCDecidedzone;
import com.hailong.bos.domain.BCStaff;
import com.hailong.bos.domain.NoticeBill;
import com.hailong.bos.domain.WorkBill;
import com.hailong.bos.domain.en.OrderType;
import com.hailong.bos.domain.en.PickStateEnum;
import com.hailong.bos.domain.en.WorkBillType;
import com.hailong.bos.service.NoticeBillService;

@Service
@Transactional
public class NoticeBillServiceImpl implements NoticeBillService {
	
	//注入crm那端的数据
	@Autowired
	private Client cilent;
	
	//注入定区的服务对象
	@Autowired
	private DecidedzoneDao decidedzoneDao;
	
	//注入通知单的数据
	@Autowired
	private NoticeBillDao noticeBillDao;
	
	//注入WorkBill的数据
	@Autowired
	private WorkBillDao workBillDao;
	
	
	
	/**
	 * 进行根据服务客户电话号来查询客户相关的信息，然后进行回显操作
	 */
	@Override
	public Object CheckCustomerTelephone(String telephone) {
		//来根据电话号码来查询客户数据
		Object obj = null;
		try {
			obj = cilent.invoke("CheckCustomerTelephone",telephone);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	//根据发货的地址来进行自动的分单操作
	public void autoDivierOrder(NoticeBill noticeBill) {
		//进行相关的自动分单的相关的操作
		//先根据客户的地址来查询定区的Id，然后根据定区id关联查询快递员的信息
		String address=noticeBill.getPickaddress();
		//得到当前的用户
		
		try {
			Object[] obj=cilent.invoke("findDecidedzoneIdByAddress",address);
			if(obj[0]!=null){
				BCDecidedzone decidedzone=decidedzoneDao.findOne((String)obj[0]);
				if(decidedzone!=null&&decidedzone.getId()!=null){
					//得到快递员的相关的数据
					BCStaff staff=decidedzone.getStaff();

					
					if(staff!=null&&staff.getId()!=null&&!"".equals(staff.getId())){
							//分单的类型为自动分单
							noticeBill.setOrderType(OrderType.AutoOrderType.getName()); 
							
	
							//则自动分单处理操作
							//创建一个工单操作
							WorkBill workBill=new WorkBill();
							workBill.setStaff(staff);
							workBill.setAttackBillTime(0);//追单次数
							workBill.setBuildtime(new Date());//当前订单的时间
							workBill.setPickstate(PickStateEnum.NoFetching.getName()); //没有取单状态
							workBill.setType(WorkBillType.NewOrder.getName()); //是一个新单
							workBill.setRemark(noticeBill.getRemark());
							workBill.setNoticeBill(noticeBill);
							
							//保存数据
							//noticeBillDao.save(noticeBill);
							//保存自动分单的数据
							workBillDao.save(workBill);
							//进行调用短信平台,进行给快递员发送短信通知
					}
				
				}
			}else{
				//进行手动分单操作
				noticeBill.setOrderType(OrderType.HandleOrderType.getName()); //手动分单
				//保存数据
				//noticeBillDao.save(noticeBill);
			}
			noticeBillDao.save(noticeBill);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
