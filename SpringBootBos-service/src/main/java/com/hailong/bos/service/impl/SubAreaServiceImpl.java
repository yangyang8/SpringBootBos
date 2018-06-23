package com.hailong.bos.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hailong.bos.dao.RegionDao;
import com.hailong.bos.dao.SubAreaDao;
import com.hailong.bos.domain.BCRegion;
import com.hailong.bos.domain.BCSubArea;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.domain.stat.StatSubarea;
import com.hailong.bos.service.SubAreaService;

@Service
@Transactional
public class SubAreaServiceImpl implements SubAreaService {
	@Autowired
	public SubAreaDao subareaDao;
	
	@Autowired
	private RegionDao regionDao;
	
	@Override
	public JsonUtils<BCSubArea> pageList(Integer page, Integer rows) {
		//进行查询有多少数据数
		long RecordNum=subareaDao.count();
		//进行排序的操作
		Sort sort=new Sort(Direction.DESC,"addresskey");
		//进行创建分页查询条件
		Pageable p=new PageRequest(page-1,rows, sort);
		
		//进行分页查询操作
		Page<BCSubArea> info=subareaDao.findAll(p);
		
		JsonUtils<BCSubArea> json=new JsonUtils<BCSubArea>(RecordNum,info.getContent());
		
		return json;
	}

	/**
	 * 进行保存和更新操作
	 */
	@Override
	public void saveOrUpdate(BCSubArea subarea) {
		subareaDao.save(subarea);
	}

	//进行批量的删除操作
	@Override
	public void deleteById(String ids) {
		for(String id:ids.split(",")){
			subareaDao.delete(id);
		}
	}

	@Override
	public List<BCRegion> getAllRegions(String q) {
		List<BCRegion> list=null;
		if(q==null||"".equals(q)){
			list=regionDao.findAll();
		}else{
			//单单加上这个like是不会进行么湖查询的，是要进行Containing之后才会进行么湖查询的相关的操作的
			list=regionDao.findByProvinceContainingOrCityContainingOrDistrictContainingOrPostcodeContainingOrShortcodeContainingOrCitycodeContaining(q, q, q, q, q, q);
		}
		return list;
	}

	@Override
	public JsonUtils<BCSubArea> pageList(Integer page, Integer rows, BCSubArea subarea) {
		//进行查询有多少数据数
		long RecordNum=subareaDao.count();
		//进行排序的操作
		Sort sort=new Sort(Direction.DESC,"addresskey");
		//进行创建分页查询条件
		Pageable p=new PageRequest(page-1,rows, sort);
		//得到我们的AddressKey
		String addresskey=subarea.getAddresskey();
		//先得到区域的数据
		BCRegion region=subarea.getRegion();
		
		
		/**
		 * 三种方式
		 * 1，先来一个判断一下subarea是不是为空
		 * 2，来一个判断addresskey是否为空
		 * 3，最后进行调用综合的方法
		 */
		//进行分页查询操作
		Page<BCSubArea> info=null;
		if(subarea!=null){
			/*if(StringUtils.isNotBlank(addresskey)&&region==null){
				//调用findByaddresskeyContaining方法
				info=subareaDao.findByAddresskeyContaining(addresskey,p);
			}else*/ if(region!=null&&StringUtils.isNotBlank(addresskey)){
				//调用踪合方法addresskey, province, city, district
				String province=region.getProvince();
				String city=region.getCity();
				String district=region.getDistrict();
				info=subareaDao.findByAddresskeyContainingAndRegion_ProvinceContainingAndRegion_CityContainingAndRegion_DistrictContaining(addresskey, province, city, district, p);
			}else{
				//这个方法调用的可能性很小
				info=subareaDao.findAll(p);
			}
		}else{
			//这个方法调用的可能性很小
			info=subareaDao.findAll(p);
		}
		JsonUtils<BCSubArea> json=new JsonUtils<BCSubArea>(RecordNum,info.getContent());
		
		return json;
	}

	//查询所有分区当中的数据
	@Override
	public List<BCSubArea> findAll() {
		return subareaDao.findAll();
	}

	@Override
	public List<StatSubarea> getStatSubareaGroupByProvice() {
		List<Object> list=subareaDao.getStatSubareaGroupByProvice();
		List<StatSubarea> statList=new ArrayList<StatSubarea>();
		StatSubarea sub=null;
		for(Object obj:list){
			sub=new StatSubarea();
			Object[] o=(Object[])obj;
			sub.setName(o[0].toString());
			sub.setValue(Double.valueOf(o[1].toString()));
			statList.add(sub);
		}
		return statList;
	}


}
