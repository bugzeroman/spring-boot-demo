package com.yuwen.spring.actuator.actuate.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("my1")
public class MyHealthIndicator implements HealthIndicator {

	private static int num = 0;

	@Override
	public Health health() {
		// 进行一些特定的健康检查
		int errorCode = check();
		if (errorCode != 0) {
			return Health.down().withDetail("Error Code", errorCode).build();
		}
		return Health.up().build();
	}

	// 这里模拟检查，设置为一次成功一次失败
	private int check() {
		num++;
		return num % 2;
	}
}
