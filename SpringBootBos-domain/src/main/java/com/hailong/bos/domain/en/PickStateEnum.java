package com.hailong.bos.domain.en;

/**
 * 取件状态
 * @author Administrator
 *
 */
public enum PickStateEnum {
	NoFetching("未取件","0"),Fetching("取件中","1"),Fetched("已取件","2");

	
    private String name ;
    private String value ;
	
	PickStateEnum(String name,String value){
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
