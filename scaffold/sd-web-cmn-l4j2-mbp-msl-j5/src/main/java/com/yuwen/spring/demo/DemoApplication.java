package com.yuwen.spring.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.Spring Boot应用 <BR/>
 * 2.MyBatis Plus Mapper接口包路径配置 <BR/>
 *
 */
@SpringBootApplication
@MapperScan("com.yuwen.spring.demo.dao")
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
