package com.yuwen.spring.demo.service.impl;

import com.yuwen.spring.demo.entity.UserEntity;
import com.yuwen.spring.demo.dao.UserDao;
import com.yuwen.spring.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yuwen
 * @since 2021-11-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

}
