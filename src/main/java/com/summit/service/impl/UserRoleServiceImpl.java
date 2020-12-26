package com.summit.service.impl;

import com.summit.dao.entity.UserRoleModel;
import com.summit.dao.repository.UserRoleDao;
import com.summit.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: jeerper
 * @since: 2020/12/26 17:22
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;
    @Override
    public List<UserRoleModel> getUserRolessByUserName(String userName) {
        List<UserRoleModel> userRoleModels = userRoleDao.getUserRolessByUserName(userName);
        return userRoleModels;
    }
}
