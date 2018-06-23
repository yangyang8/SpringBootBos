package com.hailong.bos.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hailong.bos.dao.DecidedzoneDao;
import com.hailong.bos.dao.StaffDao;
import com.hailong.bos.dao.SubAreaDao;
import com.hailong.bos.domain.BCDecidedzone;
import com.hailong.bos.domain.BCStaff;
import com.hailong.bos.domain.BCSubArea;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.service.DecidedzoneService;

@Service
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService{
	
	@Autowired
	private DecidedzoneDao decidedzoneDao;
	
	//注入StaffDao对象
	@Autowired
	private StaffDao staffDao;
	
	@Autowired
	private SubAreaDao subareaDao;
	
/*	@Autowired
	JdbcTemplate template;*/

	@Override
	public List<BCStaff> getStaffAll() {
		return staffDao.findByDeltag("0");
	}

	//进行保存和更新数据
	@Override
	public void saveOrUpdate(BCDecidedzone decidedzone,String subareaId[]) {
		
		for(String id:subareaId){
			//得到我们Subarea数据
			BCSubArea subarea=subareaDao.findOne(id);
			decidedzone.getSetSubarea().add(subarea);
		}
		decidedzoneDao.save(decidedzone);
	}

	@Override
	public List<BCSubArea> getSubAreaAll() {
		return subareaDao.findByDecidedzoneIsNull();
	}

	@Override
	public JsonUtils<BCDecidedzone> getPageList(Integer page, Integer rows) {
		
		//查询总记录数
		long count=decidedzoneDao.count();
		
		Pageable p=new PageRequest(page-1, rows);
		Page<BCDecidedzone> ps=decidedzoneDao.findAll(p);
		
		JsonUtils<BCDecidedzone> json=new JsonUtils<BCDecidedzone>();
		
		json.setTotal(count);
		json.setRows(ps.getContent());
		
		return json;
	}

	//根据定区的Id来查询相关分区的数据
	@Override
	public List<BCSubArea> findAssociationSubarea(String decidedzoneId) {
		if(decidedzoneId!=null&&!"".equals(decidedzoneId)){
			//创建一个定区对象
			BCDecidedzone d=new BCDecidedzone();
			d.setId(decidedzoneId);
			List<BCSubArea> list=subareaDao.findByDecidedzone(d);
			return list;
		}else{
			return null;
		}
		
	}
	
	public BCDecidedzone findByDecidedzoneId(String decidedzoneId){
		return decidedzoneDao.findOne(decidedzoneId);
	}
	
}
