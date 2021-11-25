package com.yuwen.spring.demo.controller.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
		if (userEntity == null) {
			return null;
		}
		QueryOneUserRsp userRsp = transferUserEntity2QueryOneUserRsp(userEntity);
		return userRsp;
	}

	private QueryOneUserRsp transferUserEntity2QueryOneUserRsp(UserEntity userEntity) {
		QueryOneUserRsp userRsp = new QueryOneUserRsp();
		userRsp.setId(userEntity.getId());
		userRsp.setName(userEntity.getName());
		userRsp.setEmail(userEntity.getEmail());
		String birthday = LocalDateTimeUtil.formatNormal(userEntity.getBirthday());
		userRsp.setBirthday(birthday);
		String createTime = LocalDateTimeUtil.formatNormal(userEntity.getCreateTime());
		userRsp.setCreateTime(createTime);
		if (userEntity.getUpdateTime() != null) {
			String updateTime = LocalDateTimeUtil.formatNormal(userEntity.getUpdateTime());
			userRsp.setUpdateTime(updateTime);
		}
		return userRsp;
	}

	@Override
	public List<QueryOneUserRsp> queryBatchUser(QueryBatchUserReq userReq, Integer pageNum, Integer pageSize) {
		if (pageNum == null) {
			pageNum = 1;
		}

		if (pageSize == null) {
			pageSize = Integer.MAX_VALUE;
		}

		IPage<UserEntity> page = new Page<UserEntity>();
		page.setCurrent(pageNum);
		page.setSize(pageSize);
		// 返回的pageNew和page是同一个对象
		IPage<UserEntity> pageNew = userService.page(page);
		List<UserEntity> records = pageNew.getRecords();
		if (CollectionUtils.isEmpty(records)) {
			return null;
		}
		List<QueryOneUserRsp> results = records.stream().map(userEntity -> {
			return transferUserEntity2QueryOneUserRsp(userEntity);
		}).collect(Collectors.toList());
		return results;
	}

}
