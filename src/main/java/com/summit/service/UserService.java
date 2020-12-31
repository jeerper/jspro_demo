package com.summit.service;


import com.summit.dao.entity.UserModel;
import com.summit.util.page.Page;

import java.util.Date;

/**
 * @author: jeerper
 * @since: 2020/12/24 17:42
 */
public interface UserService {

    void insertUserInfo(UserModel userInfo);

    UserModel findUserByUserName(String username);

    Page<UserModel> findUsersByPages(String username, String name, String sex, Date start, Date end,Integer pageIndex,Integer pageSize);
}
