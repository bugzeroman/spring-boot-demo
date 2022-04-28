package com.yuwen.spring.kafka.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * 生产者服务
 *
 */
@Service
public class ProviderService {
	public static final String TOPIC = "testTopic";

	@Autowired
	private KafkaTemplate<?, String> kafkaTemplate;

	public void send(String msg) {
		// 发送消息
		kafkaTemplate.send(TOPIC, msg);
	}
}
