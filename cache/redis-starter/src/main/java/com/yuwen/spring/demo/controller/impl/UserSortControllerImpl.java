package com.yuwen.spring.demo.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.yuwen.spring.demo.controller.UserSortController;
import com.yuwen.spring.demo.entity.User;

@RestController
public class UserSortControllerImpl implements UserSortController {

	// 保存在Redis中的user集合的key
	private static final String ZSET_USER = "zset_user";

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void createUser(User user) {
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		// 重复添加相同的user会覆盖
		zset.add(ZSET_USER, user, user.getId());
		System.out.println("add user " + user + "to redis zset_user");
	}

	@Override
	public List<User> getAllUser(Integer min, Integer max) {
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();

		// 如果边界为空，使用负无穷和正无穷填充
		if (min == null) {
			min = Integer.MIN_VALUE;
		}

		if (max == null) {
			max = Integer.MAX_VALUE;
		}

		Set<Object> userObjects = zset.rangeByScore(ZSET_USER, min, max);
		System.out.println("getAllUser users=" + userObjects);

		List<User> users = Lists.transform(new ArrayList<>(userObjects), object -> (User) object);

		return users;
	}

}
