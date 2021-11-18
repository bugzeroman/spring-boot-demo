package com.yuwen.spring.jackson.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuwen.spring.demo.pojo.request.CreateUserReq;

/**
 * 使用Jackson,指定Java的请求类，生成对应的JSON字符串
 *
 */
public class JsonGenerator {
	public static void main(String[] args) throws Exception {
		CreateUserReq obj = new CreateUserReq();
		genetateJson(obj);
	}

	/**
	 * 生成对象的JSON字符串
	 */
	private static void genetateJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		System.out.println(json);
	}
}
