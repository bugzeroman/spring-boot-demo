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
		jedis.select(5);
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

		// 1.从第0个开始，一直到最后一个
		Set<String> zrange1 = jedis.zrange(key1, 0, -1);
		System.out.println("zset1 [0,-1]=" + zrange1);

		// 获取倒数第一个
		Set<String> zrange2 = jedis.zrange(key1, -1, -1);
		System.out.println("zset1 [-1,-1]=" + zrange2);

		// 获取分数在100-300之间的
		Set<String> zrangeByScore1 = jedis.zrangeByScore(key1, 100, 300);
		System.out.println("zset1 [100,300]=" + zrangeByScore1);

		// 获取分数在100-300之间的，并且返回结果携带分数
		Set<Tuple> zrangeByScoreWithScores1 = jedis.zrangeByScoreWithScores(key1, 100, 300);
		System.out.println("zset1 [100,300]=" + zrangeByScoreWithScores1);

		// 获取所有数据，返回结果从小到大排序
		Set<String> zrangeByScore2 = jedis.zrangeByScore(key1, "-inf", "+inf");
		System.out.println("zset1 [-inf,+inf]=" + zrangeByScore2);

		// 获取所有数据，返回结果从大到小排序
		Set<String> zrangeByScore3 = jedis.zrevrangeByScore(key1, "+inf", "-inf");
		System.out.println("zset1 reverse [+inf,-inf]=" + zrangeByScore3);
	}

	/**
	 * Zset删除指定分数范围内的元素
	 */
	@Test
	public void testZsetRemoveByScore() {
		String key1 = "zsetRevome";
		jedis.zadd(key1, 400d, "C");
		jedis.zadd(key1, 500d, "C++");
		jedis.zadd(key1, 100d, "Java");
		jedis.zadd(key1, 200d, "SQL");
		jedis.zadd(key1, 300d, "Python");
		Set<String> zrange1 = jedis.zrange(key1, 0, -1);
		System.out.println(key1 + "before remove =" + zrange1);

		// 删除300分以下的元素
		jedis.zremrangeByScore(key1, 0, 300);
		Set<String> zrange2 = jedis.zrange(key1, 0, -1);
		System.out.println(key1 + "after remove =" + zrange2);
	}

}