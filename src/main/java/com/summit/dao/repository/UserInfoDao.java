package com.summit.dao.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.summit.dao.entity.UserModel;

/**
 * @author: jeerper
 * @since: 2020/12/24 17:56
 */
public interface UserInfoDao extends BaseMapper<UserModel> {

    void insertUserInfo(UserModel userInfo);
}
