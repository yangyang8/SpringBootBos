package com.hailong.bos.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringToDateConfig2 implements Converter<String,Date>{

	@Override
	public Date convert(String source) {
		//创建一个日期对象
		SimpleDateFormat dataFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			if(source==null){
				return null;
			}
			date=dataFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
