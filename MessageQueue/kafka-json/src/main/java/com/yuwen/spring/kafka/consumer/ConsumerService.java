package com.yuwen.spring.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.yuwen.spring.kafka.entity.UserInfo;
import com.yuwen.spring.kafka.provider.ProviderService;

/**
 * 消费者服务类
 */
@Service
public class ConsumerService {

	@KafkaListener(topics = ProviderService.TOPIC)
	public void receive(UserInfo user) {
		System.out.println("Consumer= " + user);
	}
}
