package com.yuwen.spring.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yuwen.spring.demo.entity.User;

/**
 * 用户的增查接口,查询出来的结果按照Id排序
 * 
 * 使用Redis的Zset排序功能
 */
@RequestMapping("user/sort")
public interface UserSortController {
	/**
	 * 创建用户
	 */
	@PostMapping
	void createUser(@RequestBody User user);

	/**
	 * 查询所有用户，支持按照ID排序，并且按照ID过滤返回数据
	 */
	@GetMapping("all")
	List<User> getAllUser(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max);

}
