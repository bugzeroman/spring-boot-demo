package com.yuwen.spring.actuator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActuatorController {

	@GetMapping("hello")
	public String helloActutor() {
		return "hello actutor";
	}
}
