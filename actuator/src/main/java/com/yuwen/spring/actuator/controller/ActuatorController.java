package com.yuwen.spring.actuator.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActuatorController {

	@Value("info.description")
	private String name;

	@GetMapping("hello")
	public String helloActutor() {
		System.out.println("name=" + name);
		return "hello actutor";
	}
}
