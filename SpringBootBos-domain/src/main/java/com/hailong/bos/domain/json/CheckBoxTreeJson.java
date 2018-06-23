package com.hailong.bos.domain.json;

import java.util.ArrayList;
import java.util.List;

/**
 * 我们的权限树的展现实现类
 * @author Administrator
 *
 * @param <T>
 */
public class CheckBoxTreeJson<T> {
	
	private String id;
	private String text;
	private List<T> children=new ArrayList<T>();
	
	
	public CheckBoxTreeJson(){
		
	}
	
	public CheckBoxTreeJson(String id,String text,List<T> children){
		this.id=id;
		this.text=text;
		this.children=children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<T> getChildren() {
		return children;
	}

	public void setChildren(List<T> children) {
		this.children = children;
	}
}
