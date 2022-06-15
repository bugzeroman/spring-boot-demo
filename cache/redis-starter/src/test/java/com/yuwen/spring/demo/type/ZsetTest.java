package com.yuwen.spring.demo.type;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

@SpringBootTest
public class ZsetTest {

	private static final String ZSET_KEY = "zset-test";

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@BeforeEach
	public void beforeClass() {
		// 在调用每个测试方法前，删除数据
		redisTemplate.delete(ZSET_KEY);
	}

	@Test
	public void testZset() {
		ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
		// 0.初始化数据
		zSet.add(ZSET_KEY, "C", 400d);
		zSet.add(ZSET_KEY, "C++", 500d);
		zSet.add(ZSET_KEY, "Java", 100d);
		zSet.add(ZSET_KEY, "SQL", 200d);
		zSet.add(ZSET_KEY, "Python", 300d);

		// 1.从第0个开始，一直到最后一个
		Set<Object> zrange1 = zSet.range(ZSET_KEY, 0, -1);
		System.out.println("zset1 [0,-1]=" + zrange1);

		// 获取倒数第一个
		Set<Object> zrange2 = zSet.range(ZSET_KEY, -1, -1);
		System.out.println("zset1 [-1,-1]=" + zrange2);

		// 获取分数在100-300之间的
		Set<Object> zrangeByScore1 = zSet.rangeByScore(ZSET_KEY, 100, 300);
		System.out.println("zset1 [100,300]=" + zrangeByScore1);

		// 获取分数在100-300之间的，并且返回结果携带分数
		Set<TypedTuple<Object>> zrangeByScoreWithScores1 = zSet.rangeByScoreWithScores(ZSET_KEY, 100, 300);
		System.out.println("zset1 [100,300]=" + zrangeByScoreWithScores1);

		// 获取所有数据，返回结果从小到大排序
		Set<Object> zrangeByScore2 = zSet.rangeByScore(ZSET_KEY, Double.MIN_VALUE, Double.MAX_VALUE);
		System.out.println("zset1 [-inf,+inf]=" + zrangeByScore2);

		// 获取所有数据，返回结果从大到小排序
		Set<Object> zrangeByScore3 = zSet.reverseRangeByScore(ZSET_KEY, Double.MIN_VALUE, Double.MAX_VALUE);
		System.out.println("zset1 [-inf,+inf]=" + zrangeByScore3);

	}
}
