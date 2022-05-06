package com.yuwen.spring.kafka.entity;

/**
 * 在Kafka中传输的对象类
 *
 */
public class UserInfo {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", age=" + age + "]";
	}

}
