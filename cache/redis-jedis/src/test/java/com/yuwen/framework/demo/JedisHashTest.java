package com.yuwen.framework.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * 6.Jedis-API:Hash
 *
 */
public class JedisHashTest {

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
	public void testHash() {
		String key1 = "hash1";
		String field1 = "field1";
		String value1 = "value1";
		jedis.hset(key1, field1, value1);

		String hget1 = jedis.hget(key1, field1);
		System.out.println("hash1:field1 hget=" + hget1);

		String key2 = "hash2";
		Map<String, String> field2value = new HashMap<>();
		field2value.put("id", "90955");
		field2value.put("name", "yuwen");
		field2value.put("address", "changzhou");

		jedis.hmset(key2, field2value);

		List<String> hmget2 = jedis.hmget(key2, "id", "name");
		System.out.println("hash2:id,name hmget=" + hmget2);
	}

}