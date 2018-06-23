package com.hailong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;


//进行启动SpringBoot的自动配置
@SpringBootApplication
@EntityScan(basePackages="com.hailong.bos.domain") //一般是不用有加上这个注解的，因为spring boot内部帮我们加了
@EnableCaching
public class Application {

	public static void main(String[] args) {
		//System.out.println();
		SpringApplication.run(Application.class, args);
	}

}
