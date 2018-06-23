package com.hailong.bos.domain.en;

public enum OrderType {
	AutoOrderType("自动分单","0"),HandleOrderType("手动分单","1");
	
	private String name;
	private String value;
	
	OrderType(String name,String value){
		this.name=name;
		this.value=value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
