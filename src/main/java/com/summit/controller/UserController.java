package com.summit.controller;


import cn.hutool.core.date.DateUtil;
import com.summit.dao.entity.UserModel;
import com.summit.service.UserService;
import com.summit.util.ResponseUtil;
import com.summit.util.page.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author: jeerper
 * @since: 2020/12/24 14:56
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;



    //@UserSave
    @ApiOperation(value = "录入用户信息", notes = "返回不是-1则为成功")
    @PostMapping(value = "insertUserInfo")
    public Object addUserInfo(@RequestBody UserModel userInfo){
        try {
            userService.insertUserInfo(userInfo);
            return  ResponseUtil.sucessResponse("新增成功");
        } catch (Exception e) {
            return ResponseUtil.failedResponse("新增失败", e.getMessage());
        }

    }

    @ApiOperation(value = "根据用户名查找用户", notes = "返回不是-1则为成功")
    @RequestMapping(value = "/findUserByUserName",method = RequestMethod.GET)
    public Object findUserByUserName(@RequestParam("username") String username){
        try {
            UserModel userModel =userService.findUserByUserName(username);
            return  ResponseUtil.sucessObjectResponse(userModel);
        } catch (Exception e) {
            return ResponseUtil.failedResponse("根据用户名查找用户失败", e.getMessage());
        }
    }


    @ApiOperation(value = "查找用户列表", notes = "返回用户分页列表")
    @RequestMapping(value = "/findUsersByPages",method = RequestMethod.GET)
    public Object findUsersByPages(@RequestParam(value = "username",required = false) String username,
                                   @RequestParam(value ="name",required = false) String name,
                                   @RequestParam(value ="sex",required = false) String sex,
                                   @RequestParam(value ="startDate",required = false) String startDate,
                                   @RequestParam(value ="endDate",required = false) String endDate,
                                   @RequestParam(value = "pageIndex", required = false,defaultValue = "1")Integer pageIndex,
                                   @RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize){
        try {
            Date start = DateUtil.parse(startDate);
            Date end = DateUtil.parse(endDate);
            Page<UserModel> pagevo =userService.findUsersByPages(username,name,sex,start,end,pageIndex,pageSize);
            return  ResponseUtil.sucessObjectResponse(pagevo);
        } catch (Exception e) {
            return ResponseUtil.failedResponse("查找用户列表失败", e.getMessage());
        }
    }

}
