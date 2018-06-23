package com.hailong.bos.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONUtils {

	//把我们list集合转成json数组对象
	public static JSONArray listToJsonArray(List<?> list){
		JSONArray jsonArray=new JSONArray();
		if (list==null ||list.isEmpty()) {
            return jsonArray;//nerver return null
        }
		for(Object obj:list){
			String json=JSON.toJSONString(obj);
			jsonArray.add(json);
		}
		return jsonArray;
	}
	
	//把我们的json数组对象转成list集合对象
	/**
     * 根据JSONArray String获取到List
     * @param <T>
     * @param <T>
     * @param jArrayStr
     * @return
     */
    public static <T> List<T> getListByArray(Class<T> class1,String jArrayStr) {
    	
        List<T> list = new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(jArrayStr);
        if (jsonArray==null || jsonArray.isEmpty()) {
            return list;//nerver return null
        }
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            T t = JSONObject.toJavaObject(jsonObject, class1);
            list.add(t);
        }
        return list;
    }

}
