package com.yuwen.spring.demo.controller.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.yuwen.spring.demo.controller.UserController;
import com.yuwen.spring.demo.entity.User;

/**
 * 使用CacheManager自己管理缓存，不使用基于注解的方式, 使用redisTemplate和cacheManager都可以操作缓存，
 * 使用redisTemplate能使用redis特定的数据类型。
 *
 */
@RestController
public class UserControllerImpl implements UserController {

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public void createUser(User user) {
		// 使用redisTemplate需要手动加上缓存前缀user::
		String key = "user::id_" + user.getId();
		redisTemplate.opsForValue().set(key, user);
		System.out.println("createUser, user=" + user);
	}

	@Override
	public User upadteUser(Long id, User user) {
		System.out.println("upadteUser, id=" + id + ", user=" + user);
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		System.out.println("deleteUser, id=" + id);
	}

	@Override
	public User getOneUser(Long id) {
		// 先从缓存中读取，自动会给key加上缓存前缀user::
		Cache cache = cacheManager.getCache(USER_CACHE_NAME);
		String key = "id_" + id;
		User userCache = cache.get(key, User.class);
		System.out.println("getOneUser, find cache user=" + userCache);
		if (userCache != null) {
			return userCache;
		}

		System.out.println("getOneUser failed, create user id=" + id);
		User user = new User();
		user.setId(id);
		user.setName("yuwen");

		// 把找到的放进缓存
		cache.put(key, user);

		return user;
	}

	@Override
	public List<User> getAllUser(Integer pageNum, Integer pageSize) {
		System.out.println("getAllUser, pageNum=" + pageNum + ", pageSize=" + pageSize);
		Set<String> keys = redisTemplate.keys("*");
		List<User> users = redisTemplate.opsForValue().multiGet(keys);
		System.out.println("getAllUser users=" + users);
		return users;
	}

}
