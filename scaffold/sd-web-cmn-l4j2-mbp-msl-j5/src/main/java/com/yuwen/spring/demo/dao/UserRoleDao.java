package com.yuwen.spring.demo.dao;

import com.yuwen.spring.demo.entity.UserRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户角色表，一个用户可以关联到多个角色 Mapper 接口
 * </p>
 *
 * @author yuwen
 * @since 2021-11-16
 */
public interface UserRoleDao extends BaseMapper<UserRoleEntity> {

}
