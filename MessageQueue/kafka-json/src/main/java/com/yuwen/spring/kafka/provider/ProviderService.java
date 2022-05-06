package com.yuwen.spring.kafka.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.yuwen.spring.kafka.entity.UserInfo;

/**
 * 生产者服务类
 *
 */
@Service
public class ProviderService {
	public static final String TOPIC = "userTopic";

	@Autowired
	private KafkaTemplate<?, UserInfo> kafkaTemplate;

	public void send(UserInfo user) {
		// 发送消息
		kafkaTemplate.send(TOPIC, user);
		System.out.println("Provider= " + user);
	}
}
