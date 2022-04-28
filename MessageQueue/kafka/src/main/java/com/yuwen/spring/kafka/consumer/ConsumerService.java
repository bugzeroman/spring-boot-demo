package com.yuwen.spring.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.yuwen.spring.kafka.provider.ProviderService;

/**
 * 消费者服务
 */
@Service
public class ConsumerService {
	@KafkaListener(topics = ProviderService.TOPIC, topicPartitions = {})
	public void processMessage(String message) {
		System.out.println("Consumer= " + message);
	}
}
