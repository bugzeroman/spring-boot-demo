package com.yuwen.framework.demo;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * 4.Jedis-API:Set
 *
 */
public class JedisSetTest {

	private static Jedis jedis;

	@BeforeClass
	public static void beforeClass() {
		String host = "10.21.13.14";
		int port = 6379;
		jedis = new Jedis(host, port);

		jedis.flushDB();
	}

	@AfterClass
	public static void afterClass() {
		jedis.close();
	}

	@Test
	public void testSet() {
		String key1 = "set1";
		jedis.sadd(key1, "tom");
		jedis.sadd(key1, "jerry");
		jedis.sadd(key1, "lucy");

		Set<String> smembers = jedis.smembers(key1);
		System.out.println("set1 smembers=" + smembers);
	}

}