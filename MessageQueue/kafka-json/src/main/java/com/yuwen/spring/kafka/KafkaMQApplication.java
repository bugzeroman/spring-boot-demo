package com.yuwen.spring.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * 启用kafka,JSON序列化和反序列化对象
 * 
 */
@SpringBootApplication
@EnableKafka
public class KafkaMQApplication {
	public static void main(String[] args) {
		SpringApplication.run(KafkaMQApplication.class, args);
	}
}
