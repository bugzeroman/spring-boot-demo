package com.yuwen.spring.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户角色表，一个用户可以关联到多个角色
 * </p>
 *
 * @author yuwen
 * @since 2021-11-16
 */
@TableName("tbl_user_role")
public class UserRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.NONE)
    private Long userId;

    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.NONE)
    private String roleId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
            "userId=" + userId +
            ", roleId=" + roleId +
            ", createTime=" + createTime +
        "}";
    }
}
