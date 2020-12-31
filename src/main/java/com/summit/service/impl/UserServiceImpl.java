package com.summit.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.summit.annotation.OperationLogDetail;
import com.summit.annotation.UserOperate;
import com.summit.dao.repository.UserInfoDao;
import com.summit.dao.entity.UserModel;
import com.summit.model.enums.OperationType;
import com.summit.model.enums.OperationUnit;
import com.summit.service.UserService;
import com.summit.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    @UserOperate
    public void insertUserInfo(UserModel userInfo) {
        LocalDateTime date = LocalDateTime.now();
        userInfo.setCreateTime(date);
        userInfo.setUpdateTime(date);
        userInfo.setUserName(IdWorker.getIdStr());
        userInfoDao.insertUserInfo(userInfo);
    }

    @Override
    @OperationLogDetail(detail = "通过手机号[{{tel}}]获取用户名",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.SELECT)
    public UserModel findUserByUserName(String username) {
        return null;
    }

    @Override
    public Page<UserModel> findUsersByPages(String username, String name, String sex, Date start, Date end, Integer pageIndex,Integer pageSize) {
        Integer page=(pageIndex-1)*pageSize;
        int toatl = userInfoDao.selectCount(Wrappers.<UserModel>lambdaQuery()
                .eq(UserModel::getUserName, username)
                .eq(UserModel::getName, name)
                .eq(UserModel::getSex, sex));

        List<UserModel> userModels=userInfoDao.findUsersByPages(username, name, sex, start, end, String.valueOf(page),String.valueOf(pageSize));
        Page<UserModel> pageVo=new Page<>();
        pageVo.setRecords(userModels);
        pageVo.setTotal(toatl);
        pageVo.setCurrentPage(pageIndex);
        pageVo.setPageSize(pageSize);
        return pageVo;
    }
}
