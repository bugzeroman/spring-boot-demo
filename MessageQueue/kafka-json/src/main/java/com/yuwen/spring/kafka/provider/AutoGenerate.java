package com.yuwen.spring.kafka.provider;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuwen.spring.kafka.entity.UserInfo;

/**
 * 自动生成UserInfo类的实例，作为生产者向kafka发送消息
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
				int age = 0;
				while (true) {
					UserInfo user = new UserInfo();
					user.setName("zhangsan");
					user.setAge(++age);

					providerService.send(user);
					try {
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		t.start();
	}

}
