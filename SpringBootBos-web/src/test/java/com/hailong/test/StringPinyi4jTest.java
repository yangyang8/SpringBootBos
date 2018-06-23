package com.hailong.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.hailong.bos.utils.PinYin4jUtils;

/**
 * 进行测试我们的汉字拼音操作
 * @author Administrator
 *
 */
public class StringPinyi4jTest {
	
	@Test
	public void TestPinyi(){
		String city="石家庄市";
	    String provice="河北省";
	    String distict="桥西区";
	    
	    //进行分别去除最后一个字符串操作
	    city=city.substring(0,city.length()-1);
	    provice=provice.substring(0,provice.length()-1);
	    distict=distict.substring(0,distict.length()-1);
	    
	    //进行拼接成一个字符串
	    String info=provice+""+city+""+distict;
	    //得到他们的简码，首字母大写，且只取每个字的第一个字母
	    String[] shortCodes=PinYin4jUtils.getHeadByString(info);
	    String shortCode=StringUtils.join(shortCodes);
	    System.out.println(shortCode);
	    
	    //进行得到城市的编码操作
	    String cityCode=PinYin4jUtils.hanziToPinyin(city,"");
	    System.out.println(cityCode);
	}
	
	@Test
	public void TestPinyi2(){
		String city="广东省";
	    String provice="湛江市";
	    String distict="雷州市";
	    
	    //进行分别去除最后一个字符串操作
	    city=city.substring(0,city.length()-1);
	    provice=provice.substring(0,provice.length()-1);
	    distict=distict.substring(0,distict.length()-1);
	    
	    //进行拼接成一个字符串
	    String info=provice+""+city+""+distict;
	    //得到他们的简码，首字母大写，且只取每个字的第一个字母
	    String[] shortCodes=PinYin4jUtils.getHeadByString(info);
	    String shortCode=StringUtils.join(shortCodes);
	    System.out.println(shortCode);
	    
	    //进行得到城市的编码操作
	    String cityCode=PinYin4jUtils.hanziToPinyin(city,"");
	    System.out.println(cityCode);
	}
}
