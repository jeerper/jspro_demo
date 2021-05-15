package com.summit.controller;


import cn.hutool.core.date.DateUtil;
import com.summit.annotation.UserOperate;
import com.summit.dao.entity.UserModel;
import com.summit.service.UserService;
import com.summit.util.ResponseUtil;
import com.summit.util.page.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author: jeerper
 * @since: 2020/12/24 14:56
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;



    @UserOperate
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
            logger.error("查找用户列表失败",e);
            return ResponseUtil.failedResponse("查找用户列表失败", e.getMessage());
        }
    }
    @ApiOperation(value = "导入", notes = "根据Excel内容导入用户信息")
    @RequestMapping(value = "/fileImport")
    public Object fileImport(@RequestParam(value = "file",required = true) MultipartFile file,
                                   @RequestParam(value ="username",required = false) String username,
                                   @RequestParam(value ="sex",required = false) String sex){
        try {
            String  message =userService.fileImport(username,sex,file);
            if (StringUtils.isNotBlank(message)){
                return  ResponseUtil.failedResponse("部分数据导入失败",message);
            }else {
                return  ResponseUtil.sucessObjectResponse("导入成功");
            }
        } catch (Exception e) {
            logger.error("导入失败",e);
            return ResponseUtil.failedResponse("查找用户列表失败", e.getMessage());
        }
    }
    @ApiOperation(value = "导出模板", notes = "导出用户信息")
    @RequestMapping(value = "/exportTemplate")
    public void exportTemplate(HttpServletRequest request, HttpServletResponse response){
        try {
            userService.exportTemplate(request,response);
        } catch (Exception e) {
            logger.error("导出失败",e);
        }
    }
    @ApiOperation(value = "导出数据", notes = "导出用户信息")
    @RequestMapping(value = "/export")
    public void export(UserModel user,HttpServletRequest request, HttpServletResponse response){
        try {
            userService.export(user,request,response);
        } catch (Exception e) {
            logger.error("导出数据失败",e);
        }
    }

}
