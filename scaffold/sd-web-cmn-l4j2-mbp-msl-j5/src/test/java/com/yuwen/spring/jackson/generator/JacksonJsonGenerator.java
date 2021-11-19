package com.yuwen.spring.jackson.generator;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yuwen.spring.demo.entity.UserEntity;

/**
 * 使用Jackson,指定Java的请求类，生成对应的JSON字符串
 *
 */
public class JacksonJsonGenerator {
	// 指定需要生成JSON的Java类
	public static Class<?> targetClass = UserEntity.class;

	public static void main(String[] args) throws Exception {
		Object object = targetClass.newInstance();

		// 根据属性类型给属性设默认值
		FieldCallback fc = new FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				Class<?> fieldType = field.getType();

				// final修饰的字段无法设置新的值
				if (Modifier.isFinal(field.getModifiers())) {
					return;
				}

				String type = fieldType.getSimpleName();
				Object fieldValue = StringUtils.EMPTY;
				// System.out.println(type);
				switch (type) {
				case "String":
					fieldValue = StringUtils.EMPTY;
					break;
				case "int":
				case "Integer":
					fieldValue = 0;
					break;
				case "long":
				case "Long":
					fieldValue = 0L;
					break;
				case "LocalDate":
					fieldValue = LocalDate.now();
					break;
				case "LocalDateTime":
					fieldValue = LocalDateTime.now();
					break;
				default:
					fieldValue = StringUtils.EMPTY;
					break;
				}
				try {
					field.setAccessible(true);
					field.set(object, fieldValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		ReflectionUtils.doWithFields(targetClass, fc);

		genetateJson(object);
	}

	/**
	 * 生成对象的JSON字符串
	 */
	private static void genetateJson(Object object) throws JsonProcessingException {
		System.out.println(object);
		ObjectMapper mapper = new ObjectMapper();
		// 用于序列化Java8新的时间类型
		mapper.registerModule(new JavaTimeModule());
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		System.out.println(json);
	}
}
