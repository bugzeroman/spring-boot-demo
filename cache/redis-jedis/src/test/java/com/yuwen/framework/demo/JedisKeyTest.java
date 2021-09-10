package com.yuwen.framework.demo;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * 1.Jedis-API:Key
 *
 */
public class JedisKeyTest {

	private static Jedis jedis;

	@BeforeClass
	public static void beforeClass() {
		String host = "10.21.13.14";
		int port = 6379;
		jedis = new Jedis(host, port);
	}

	@AfterClass
	public static void afterClass() {
		jedis.close();
	}

	@Test
	public void testKeys() {
		jedis.set("k1", "v1");
		jedis.set("k2", "v2");
		jedis.set("k3", "v3");

		Set<String> keys = jedis.keys("*");
		System.out.println(keys);

		String key = "k1";
		String value = jedis.get(key);
		System.out.println(key + " value=" + value);

		Boolean exists = jedis.exists(key);
		System.out.println(key + " exists=" + exists);

		Long ttl = jedis.ttl(key);
		System.out.println(key + " ttl=" + ttl);

		Long del = jedis.del("k1", "k2", "k3");
		System.out.println("k1,k2,k3" + " del=" + del);
	}

}
