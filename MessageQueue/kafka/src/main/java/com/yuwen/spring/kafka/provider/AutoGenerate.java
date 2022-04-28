package com.yuwen.spring.kafka.provider;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自动生成随机字符串，作为生产者向kafka发送消息
 */
@Component
public class AutoGenerate implements InitializingBean {

	@Autowired
	private ProviderService providerService;

	@Override
	public void afterPropertiesSet() throws Exception {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					String message = UUID.randomUUID().toString();
					providerService.send(message);
					System.out.println("Provider= " + message);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		t.start();
	}

}
