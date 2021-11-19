package com.yuwen.spring.jackson.generator;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.yuwen.spring.demo.entity.UserEntity;

/**
 * 使用Jackson,指定Java的POJO简单对象类，生成对应的JSON字符串
 */
public class JacksonJsonGenerator {
	// 指定需要生成JSON的Java类
	public static Class<?> targetClass = UserEntity.class;

	public static void main(String[] args) throws Exception {
		Object object = initObjectDefaultValue(targetClass);

		genetateJson(object);
	}

	/**
	 * 初始化对象字段的默认值
	 */
	private static Object initObjectDefaultValue(Class<?> targetClass)
			throws InstantiationException, IllegalAccessException {
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
		return object;
	}

	/**
	 * 生成对象的JSON字符串
	 */
	private static void genetateJson(Object object) throws JsonProcessingException {
		System.out.println("POJO对象：");
		System.out.println(object);

		ObjectMapper mapper = new ObjectMapper();
		// 用于序列化Java8新的时间类型
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDateTime.class,
				new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
		javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
		mapper.registerModule(javaTimeModule);
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);

		System.out.println("JSON字符串：");
		System.out.println(json);
	}
}
