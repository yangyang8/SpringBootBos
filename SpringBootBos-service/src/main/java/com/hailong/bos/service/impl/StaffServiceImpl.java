package com.hailong.bos.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hailong.bos.dao.StaffDao;
import com.hailong.bos.dao.StandardDao;
import com.hailong.bos.domain.BCStaff;
import com.hailong.bos.domain.BCStandard;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.service.StaffService;

@Service
@Transactional //增加事务操作，只有在服务层开启事务才是有效的
public class StaffServiceImpl implements StaffService {
	//定义全局的配置文件
	private final String CHCHA_NAME="demo";
	private final String CHECK_ALL_KEY="STAFF";
	
	//注入Staff的dao层数据
	@Autowired
	private StaffDao staffDao;
	
	@Autowired
	private StandardDao standardDao;
	
	//@CachePut(cacheNames="staff",key="#BCStaff.id")//value="BCStaff",key="#BCStaff.id"
	@CachePut(value=CHCHA_NAME,key=CHECK_ALL_KEY)
	//TODO 存在的问题是现在的数据不能用缓存的相关的操作
	@Transactional
	public BCStaff save(BCStaff staff,String sid) {
		//先查询出我们的标签的数据
		BCStandard standard=standardDao.findOne(sid);
		staff.setStandard(standard);
		staff.setId(UUID.randomUUID().toString());
		BCStaff bcstaff=staffDao.save(staff);
		return bcstaff;
	}
	
	//@Cacheable(cacheNames="staff",key="#BCStaff.id")
	public List<BCStaff> list() {
		return staffDao.findAll();
	}

	//根据分页来查询我们的快递员的所有数据,同时封装我们数据到StaffJson对象当中
	@Transactional  //加上事务的操作
	@Override
	//@Cacheable(cacheNames="staff",key="#rows[0].id")
	public JsonUtils pageList(Integer startIndex, Integer pageSize) {
		//return staffDao.pageList(startIndex-1, pageSize);
		//创建一个分页器
		Pageable page=new PageRequest(startIndex-1, pageSize);
		//Pageable page=new PageRequest(0,1);
		Page<BCStaff> p=staffDao.findByUsernameNot("xxx",page);
		
		//获取总的记录数据
		Long total = staffDao.count();
		
		//创建一个对象进行封封装成JSON对象
		JsonUtils json=new JsonUtils(total,p.getContent());
		
		return json;
	}
	
	//逻辑删除数据
	@Override
	//@CachePut(cacheNames="staff",key="#staff.id")
	public BCStaff operatorById(String ids,String deltag) {
		//分割ids
		//String id[]=ids.split(","); //在sql当中的数据占位符只能是字符串，不能是数组类型的数据，否则会出现如下的错误
		//Encountered array-valued parameter binding, but was expecting [java.lang.String (n/a)]
		/**
		 * 优化的地方，下次做到只更新那些本身是0的数据，而那么
		 * 已经是1是就不用再进行更新了
		 * TODO
		 */
		String str[]=ids.split(",");
		List<String> list=Arrays.asList(str);
		staffDao.updateDeltagById(deltag,list);
		return null;
	}

	//进行根据快递员的数据
	/**
	 * 优化处理
	 * 可以实现不用再从数据库当中查询数据，然后把没有修改的数据封装好，再进行更新操作
	 * 而是根据我们的id来动态的更新数据 ,使用mybatis则没有 这个问题
	 */
	@Override
	//@CachePut(cacheNames="staff",key="#staff.id")//
	public BCStaff update(BCStaff staff,String sid) {
		
		
		//先查询出我们的标签的数据
		BCStandard standard=standardDao.findOne(sid);
		
		//根据id查询数据
		BCStaff sourceStaff=staffDao.findById(staff.getId());
		sourceStaff.setStandard(standard);
		//进行更新我们的staff当中更新的数据
		sourceStaff.setHaspda(staff.getHaspda());
		//sourceStaff.setStandard(staff.getStandard());
		sourceStaff.setStation(staff.getStation());
		sourceStaff.setTelephone(staff.getTelephone());
		sourceStaff.setUsername(staff.getUsername());
		
		
		BCStaff bcstaff=staffDao.save(sourceStaff); //有则更新，没有则插入
		//进行更新操作
		//staffDao.updateById(staff);
		return bcstaff;
	}

	@Override
	public List<BCStandard> getStandards() {
		//TODO 这种方式的效率太低了，下一个同期要把进行优化操作
		List<BCStandard> list=standardDao.findByDeltag("0");
		
		return list;
	}
}
