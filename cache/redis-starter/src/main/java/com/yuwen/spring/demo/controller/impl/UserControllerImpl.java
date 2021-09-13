package com.yuwen.spring.demo.controller.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RestController;

import com.yuwen.spring.demo.controller.UserController;
import com.yuwen.spring.demo.entity.User;

/**
 * 使用CacheManager自己管理缓存，不使用基于注解的方式
 *
 */
@RestController
public class UserControllerImpl implements UserController {

	@Autowired
	private CacheManager cacheManager;

	@Override
	public void createUser(User user) {
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
		// 先从缓存中读取
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
		User user = new User();
		user.setId(999L);
		user.setName("all");
		user.setBirthday(new Date());
		return Collections.singletonList(user);
	}

}
