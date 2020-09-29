package com.yuwen.spring.actuator.actuate.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Component;

@Component
public class My2HealthIndicator extends AbstractHealthIndicator {

	private static int num = 0;

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		int errorCode = check();
		if (errorCode != 0) {
			builder.down().withDetail("code", errorCode).withDetail("num", num).build();
			return;
		}
		builder.up().withDetail("code", errorCode).withDetail("num", num).build();
	}

	// 这里模拟检查，设置为一次成功一次失败
	private int check() {
		num++;
		return num % 2;
	}
}
