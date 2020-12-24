package com.summit.service.impl;

import com.summit.dao.repository.UserInfoDao;
import com.summit.dao.entity.UserModel;
import com.summit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: jeerper
 * @since: 2020/12/24 17:43
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Override
    public void insertUserInfo(UserModel userInfo) {
        userInfoDao.insertUserInfo(userInfo);
    }
}
