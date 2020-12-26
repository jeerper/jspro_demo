package com.summit.service;

import com.summit.dao.entity.UserRoleModel;

import java.util.List;

/**
 * @author: jeerper
 * @since: 2020/12/26 17:22
 */
public interface UserRoleService {
    List<UserRoleModel>  getUserRolessByUserName(String userName);
}
