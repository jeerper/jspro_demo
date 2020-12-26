package com.summit.dao.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.summit.dao.entity.UserRoleModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: jeerper
 * @since: 2020/12/26 17:35
 */
public interface UserRoleDao extends BaseMapper<UserRoleModel> {
    List<UserRoleModel> getUserRolessByUserName(@Param("userName") String userName);
}
