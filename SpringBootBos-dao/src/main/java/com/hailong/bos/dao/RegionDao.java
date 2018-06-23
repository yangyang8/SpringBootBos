package com.hailong.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hailong.bos.domain.BCRegion;

@Repository
public interface RegionDao extends JpaRepository<BCRegion,String>{

	//根据名称进行分页查询操作
	//根据传过的数据进行模糊查询所有的数据
	public List<BCRegion> findByProvinceContainingOrCityContainingOrDistrictContainingOrPostcodeContainingOrShortcodeContainingOrCitycodeContaining
	(String province,String city,String district,String postcode,String shortcode,String citycode);
	

}
