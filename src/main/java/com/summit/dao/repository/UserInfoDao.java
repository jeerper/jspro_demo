package com.summit.dao.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.summit.dao.entity.UserModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: jeerper
 * @since: 2020/12/24 17:56
 */
public interface UserInfoDao extends BaseMapper<UserModel> {

    void insertUserInfo(UserModel userInfo);

    List<UserModel> findUsersByPages(@Param("username") String username,
                                     @Param("name") String name,
                                     @Param("sex")String sex,
                                     @Param("startDate")Date startDate,
                                     @Param("endDate")Date endDate,
                                     @Param("startPage")String start,
                                     @Param("pageSize")String pageSize);

    int findUsersCount(@Param("username") String username,
                       @Param("name") String name,
                       @Param("sex")String sex,
                       @Param("startDate")Date startDate,
                       @Param("endDate")Date endDate);

    void insertBach(List<UserModel> users);
}
