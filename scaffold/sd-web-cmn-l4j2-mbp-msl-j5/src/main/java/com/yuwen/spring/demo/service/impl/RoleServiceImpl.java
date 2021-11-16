package com.yuwen.spring.demo.service.impl;

import com.yuwen.spring.demo.entity.RoleEntity;
import com.yuwen.spring.demo.dao.RoleDao;
import com.yuwen.spring.demo.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author yuwen
 * @since 2021-11-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

}
