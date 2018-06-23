package com.hailong.bos.domain.en;

/**
 * 工单的类型
 * 有4类类型
 */
public enum WorkBillType {
	 //新单，改单，销单，追单
	NewOrder("新单","0"),ModOrder("改单","1"),CanOrder("销单","2"),AppOrder("追单","3");
	
	
	private String name;
	private String value;
	
	WorkBillType(String name,String value){
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
