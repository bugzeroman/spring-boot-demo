package com.yuwen.framework.demo;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * 2.Jedis-API:String
 *
 */
public class JedisStringTest {

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
	public void testSetString() {
		jedis.set("s1", "v1");
		String value = jedis.get("s1");
		System.out.println("s1 value=" + value);

	}

	@Test
	public void testMsetString() {
		jedis.mset("ms1", "mv1", "ms2", "mv2", "ms3", "mv3");
		List<String> mget = jedis.mget("ms1", "ms2", "ms3");
		System.out.println("ms1,2,3, values=" + mget);
	}

}