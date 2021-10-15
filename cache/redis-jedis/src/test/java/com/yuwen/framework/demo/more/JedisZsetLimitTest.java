package com.yuwen.framework.demo.more;

import java.util.List;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * 测试Zset的value数量的最大值
 * 
 * @author yuwen
 *
 */
public class JedisZsetLimitTest {
	private static Jedis jedis;

	@BeforeClass
	public static void beforeClass() {
		String host = "10.21.13.14";
		int port = 6379;
		jedis = new Jedis(host, port);
		jedis.select(5);
		jedis.flushDB();
	}

	@AfterClass
	public static void afterClass() {
		jedis.close();
	}

	/**
	 * 测试有序集合Sorted Set内的元素数量是否有限制， 实际发现没有限制， 未测试官方说明的512M的限制。
	 */
	@Test
	public void testZsetLimit() {
		String key1 = "zsetLimit";
		Double score = 1d;
		int size = 0;
		while (true) {
			for (int i = 0; i < 100; i++) {
				score++;
				String content = getContent();
				jedis.zadd(key1, score, content);
			}
			size++;
			System.out.println("add size=" + 100 * size);
		}

	}

	private String getContent() {
		String content = UUID.randomUUID().toString();
		List<String> nCopies = java.util.Collections.nCopies(100, content);
		return nCopies.toString();
	}
}
