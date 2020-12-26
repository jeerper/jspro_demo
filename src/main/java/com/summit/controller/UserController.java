package com.summit.controller;


import com.summit.annotation.UserSave;
import com.summit.dao.entity.UserModel;
import com.summit.service.UserService;
import com.summit.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



    @UserSave
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
}
