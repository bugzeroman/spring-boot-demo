package com.yuwen.spring.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yuwen.spring.demo.pojo.request.CreateUserReq;
import com.yuwen.spring.demo.pojo.request.QueryBatchUserReq;
import com.yuwen.spring.demo.pojo.request.UpdateUserReq;
import com.yuwen.spring.demo.pojo.response.QueryOneUserRsp;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yuwen
 * @since 2021-11-16
 */
@RequestMapping("/user")
public interface UserController {

	@PostMapping
	void createUser(@RequestBody CreateUserReq userReq);

	@PutMapping
	void updateUser(@RequestBody UpdateUserReq userReq);

	@DeleteMapping("{id}")
	void deleteUser(@PathVariable Long id);

	@GetMapping("one/{id}")
	QueryOneUserRsp queryOneUser(@PathVariable Long id);

	@PostMapping("batch")
	void queryBatchUser(@RequestBody QueryBatchUserReq userReq, @RequestParam("pageNum") Integer pageNum,
			@RequestParam("pageSize") Integer pageSize);
}
