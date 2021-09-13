package com.yuwen.framework.demo;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

/**
 * 5.Jedis-API:Zset
 *
 */
public class JedisZsetTest {

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
	public void testZset() {
		String key1 = "zset1";
		jedis.zadd(key1, 400d, "C");
		jedis.zadd(key1, 500d, "C++");
		jedis.zadd(key1, 100d, "Java");
		jedis.zadd(key1, 200d, "SQL");
		jedis.zadd(key1, 300d, "Python");

		Set<String> zrange1 = jedis.zrange(key1, 0, -1);
		System.out.println("zset1 [0,-1]=" + zrange1);

		Set<String> zrangeByScore1 = jedis.zrangeByScore(key1, 100, 300);
		System.out.println("zset1 [100,300]=" + zrangeByScore1);

		Set<Tuple> zrangeByScoreWithScores1 = jedis.zrangeByScoreWithScores(key1, 100, 300);
		System.out.println("zset1 [100,300]=" + zrangeByScoreWithScores1);

		Set<String> zrangeByScore2 = jedis.zrangeByScore(key1, "-inf", "+inf");
		System.out.println("zset1 [-inf,+inf]=" + zrangeByScore2);
	}

}