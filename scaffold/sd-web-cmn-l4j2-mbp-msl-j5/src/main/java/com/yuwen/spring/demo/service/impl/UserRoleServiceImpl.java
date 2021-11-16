package com.yuwen.spring.demo.service.impl;

import com.yuwen.spring.demo.entity.UserRoleEntity;
import com.yuwen.spring.demo.dao.UserRoleDao;
import com.yuwen.spring.demo.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表，一个用户可以关联到多个角色 服务实现类
 * </p>
 *
 * @author yuwen
 * @since 2021-11-16
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleEntity> implements UserRoleService {

}
