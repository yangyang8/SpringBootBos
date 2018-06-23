package com.hailong.bos.domain.json;

import java.util.List;

public class JsonUtils<T> {
	
	private Long total;
	
	private List<T> rows;

	public JsonUtils(){}
	
	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public JsonUtils(Long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	
	public Long getTotal() {
		return total;
	}
	
	public void setTotal(Long total) {
		this.total = total;
	}

}
