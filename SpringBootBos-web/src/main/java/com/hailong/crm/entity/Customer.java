package com.hailong.crm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Customer表的相关的操作
 * @author Administrator
 *
 */

@Entity
@Table(name="t_customer")
public class Customer {
	
	@Id
	@GenericGenerator(name="customer",strategy="uuid")
	private String id;
	private String name;
	private String station;
	private String telephone;
	private String address;
	
	private String decidedzone_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDecidedzone_id() {
		return decidedzone_id;
	}

	public void setDecidedzone_id(String decidedzone_id) {
		this.decidedzone_id = decidedzone_id;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", station=" + station + ", telephone=" + telephone
				+ ", address=" + address + ", decidedzone_id=" + decidedzone_id + "]";
	}
}
