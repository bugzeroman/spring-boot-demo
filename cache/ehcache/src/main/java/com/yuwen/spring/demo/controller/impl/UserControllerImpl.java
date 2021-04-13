package com.yuwen.spring.demo.controller.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.yuwen.spring.demo.controller.UserController;
import com.yuwen.spring.demo.entity.User;

@RestController
public class UserControllerImpl implements UserController {

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
		System.out.println("getOneUser, id=" + id);
		User user = new User();
		user.setId(id);
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
