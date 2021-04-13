package com.yuwen.spring.demo.controller;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yuwen.spring.demo.entity.User;

/**
 * 用户的增删改查相关接口
 */
@CacheConfig(cacheNames = "user")
@RequestMapping("user")
public interface UserController {
	/**
	 * 创建用户
	 */
	@PostMapping
	void createUser(@RequestBody User user);

	/**
	 * 更新用户
	 */
	@CachePut(key = "targetClass.getName() + #id")
	@PutMapping("{id}")
	User upadteUser(@PathVariable Long id, @RequestBody User user);

	/**
	 * 删除用户
	 */
	@DeleteMapping("{id}")
	void deleteUser(@PathVariable("id") Long id);

	/**
	 * 查询指定用户
	 */
	@Cacheable(key = "targetClass.getName() + #id")
	@GetMapping("one/{id}")
	User getOneUser(@PathVariable Long id);

	/**
	 * 查询所有用户，支持分页
	 */
	@GetMapping("all")
	List<User> getAllUser(@RequestParam(required = false) Integer pageNum,
			@RequestParam(required = false) Integer pageSize);

}
