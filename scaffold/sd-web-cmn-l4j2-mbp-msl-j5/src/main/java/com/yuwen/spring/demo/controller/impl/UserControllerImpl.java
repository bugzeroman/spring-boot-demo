package com.yuwen.spring.demo.controller.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yuwen.spring.demo.controller.UserController;
import com.yuwen.spring.demo.entity.UserEntity;
import com.yuwen.spring.demo.pojo.request.CreateUserReq;
import com.yuwen.spring.demo.pojo.request.QueryBatchUserReq;
import com.yuwen.spring.demo.pojo.request.UpdateUserReq;
import com.yuwen.spring.demo.pojo.response.QueryOneUserRsp;
import com.yuwen.spring.demo.service.UserService;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;

@RestController
public class UserControllerImpl implements UserController {

	@Autowired
	private UserService userService;

	@Override
	public void createUser(CreateUserReq userReq) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userReq.getId());
		userEntity.setName(userReq.getName());
		userEntity.setEmail(userReq.getEmail());
		if (StringUtils.isNoneBlank(userReq.getBirthday())) {
			LocalDate birthday = LocalDateTimeUtil.parseDate(userReq.getBirthday(), DatePattern.NORM_DATE_FORMATTER);
			userEntity.setBirthday(birthday);
		}

		LocalDateTime createTime = LocalDateTime.now();
		if (StringUtils.isNoneBlank(userReq.getCreateTime())) {
			createTime = LocalDateTimeUtil.parse(userReq.getCreateTime(), DatePattern.NORM_DATETIME_FORMATTER);
		}
		userEntity.setCreateTime(createTime);

		userService.save(userEntity);
	}

	@Override
	public void updateUser(UpdateUserReq userReq) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userReq.getId());
		userEntity.setName(userReq.getName());
		userEntity.setEmail(userReq.getEmail());
		if (StringUtils.isNoneBlank(userReq.getBirthday())) {
			LocalDate birthday = LocalDateTimeUtil.parseDate(userReq.getBirthday(), DatePattern.NORM_DATE_FORMATTER);
			userEntity.setBirthday(birthday);
		}
		userService.updateById(userEntity);
	}

	@Override
	public void deleteUser(Long id) {
		userService.removeById(id);
	}

	@Override
	public QueryOneUserRsp queryOneUser(Long id) {
		UserEntity userEntity = userService.getById(id);
		QueryOneUserRsp userRsp = new QueryOneUserRsp();
		userRsp.setId(userEntity.getId());
		userRsp.setName(userEntity.getName());
		userRsp.setEmail(userEntity.getEmail());
		String birthday = LocalDateTimeUtil.formatNormal(userEntity.getBirthday());
		userRsp.setBirthday(birthday);
		String createTime = LocalDateTimeUtil.formatNormal(userEntity.getCreateTime());
		userRsp.setCreateTime(createTime);
//		String updateTime = LocalDateTimeUtil.formatNormal(userEntity.getUpdateTime());
//		userRsp.setUpdateTime(updateTime);

		return userRsp;
	}

	@Override
	public void queryBatchUser(QueryBatchUserReq userReq, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub

	}

}
