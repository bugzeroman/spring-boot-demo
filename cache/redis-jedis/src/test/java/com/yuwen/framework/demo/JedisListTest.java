package com.yuwen.framework.demo;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * 3.Jedis-API:List
 *
 */
public class JedisListTest {

	private static Jedis jedis;

	@BeforeClass
	public static void beforeClass() {
		String host = "10.21.13.14";
		int port = 6379;
		jedis = new Jedis(host, port);
		// 指定缓存数据库
		jedis.select(3);

		jedis.flushDB();
	}

	@AfterClass
	public static void afterClass() {
		jedis.close();
	}

	@Test
	public void testList() {
		jedis.lpush("list1", "lv1", "lv2", "lv3");
		List<String> list1 = jedis.lrange("list1", 0, -1);
		System.out.println("list1 lrange=" + list1);

		jedis.rpush("list2", "rv1", "rv2", "rv3");
		List<String> list2 = jedis.lrange("list2", 0, -1);
		System.out.println("list2 lrange=" + list2);

		jedis.rpop("list2");
		jedis.lpop("list2");
		list2 = jedis.lrange("list2", 0, -1);
		System.out.println("list2 lrange=" + list2);

	}

	/**
	 * 设置列表固定长度
	 */
	@Test
	public void testListFixedSize() {
		String key = "list-fix";
		jedis.rpush(key, "v1", "v2", "v3", "v4", "v5");
		List<String> list1 = jedis.lrange(key, 0, -1);
		System.out.println(key + " before lrange=" + list1);

		long start = 0;
		long stop = -1;
		jedis.ltrim(key, start, stop);
		List<String> list2 = jedis.lrange(key, 2, -1);
		System.out.println(key + " after lrange=" + list2);
	}

}