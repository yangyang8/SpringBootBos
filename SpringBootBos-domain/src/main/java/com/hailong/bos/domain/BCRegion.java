package com.hailong.bos.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 基础设置区域表  国家制订的，不能改变
 * @author Administrator
 *
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class BCRegion implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="BCRegion")
	@GenericGenerator(name="BCRegion",strategy="assigned")  //assingned表示为自己指定主键的值
	private String id; //城市编号
	private String province; //省分
	private String city; //城市
	private String district; //区(县)域
	private String postcode;   //邮编
	private String shortcode;  //简码，也也就是把区域信息进行了缩写(一般就城市的拼音的简写)
	private String citycode;  //城市编码
	
	@JoinColumn(name="region_id")
	//@OneToMany(fetch=FetchType.EAGER) //关闭廷时加载操作,二边都要写才可以
	@OneToMany(fetch=FetchType.LAZY)
	@JSONField(serialize=false)  //排除转Json操作,处理json操作
	private Set<BCSubArea> subAreas=new HashSet<BCSubArea>();  //我们的区域当中有多个分区 【多对一】
	
	public BCRegion(){}
	
	public BCRegion(String id, String province, String city, String district, String postcode, String shortcode,
			String citycode, Set<BCSubArea> subAreas) {
		super();
		this.id = id;
		this.province = province;
		this.city = city;
		this.district = district;
		this.postcode = postcode;
		this.shortcode = shortcode;
		this.citycode = citycode;
		this.subAreas = subAreas;
	}
	public Set<BCSubArea> getSubAreas() {
		return subAreas;
	}
	public void setSubAreas(Set<BCSubArea> subAreas) {
		this.subAreas = subAreas;
	}
	
	//提供多个字段
	public String getName(){
		return province+" "+city+" "+district;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getShortcode() {
		return shortcode;
	}
	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	
	
}