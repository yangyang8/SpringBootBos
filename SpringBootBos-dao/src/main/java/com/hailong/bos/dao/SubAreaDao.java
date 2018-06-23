package com.hailong.bos.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hailong.bos.domain.BCDecidedzone;
import com.hailong.bos.domain.BCSubArea;

@Repository
public interface SubAreaDao extends JpaRepository<BCSubArea, Serializable>{
	
	
	//进行关联查询我们的分区表当中的数据
	public Page<BCSubArea> findByAddresskeyContainingAndRegion_ProvinceContainingAndRegion_CityContainingAndRegion_DistrictContaining(
			String addresskey,String province,String city,String district,Pageable pageable);
	
	//进行模糊查询addressKey相关的查询操作,没有用到
	public Page<BCSubArea> findByAddresskeyContaining(String addresskey,Pageable pageable);

	//得到所有的没有分区中没有定区的数据
	public List<BCSubArea> findByDecidedzoneIsNull();

	@Query("select r.province,count(1) from BCSubArea s LEFT OUTER JOIN s.region r group by r.province")
	public List<Object> getStatSubareaGroupByProvice();

	//根据定区的id来查询分区的数据
	public List<BCSubArea> findByDecidedzone(BCDecidedzone d);

}
