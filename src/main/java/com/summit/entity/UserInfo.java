package com.summit.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author: jeerper
 * @since: 2020/12/16 15:49
 */
@Data
public class UserInfo{
    @ApiModelProperty(value = "登录名称", name = "userName", required = true)
    @TableField(value = "USERNAME")
    private String userName;
    @ApiModelProperty(value = "姓名", name = "name", required = true)
    @TableField(value = "NAME")
    private String name;
    @ApiModelProperty(value = "性别", name = "sex", allowableValues = "1,2")
    @TableField(value = "SEX")
    private String sex;
    @ApiModelProperty(value = "账户密码", name = "password", required = true)
    @TableField(value = "PASSWORD")
    private String password;
    @ApiModelProperty(value = "邮箱", name = "email")
    @TableField(value = "EMAIL")
    private String email;
    @ApiModelProperty(value = "电话号码", name = "phoneNumber")
    @TableField(value = "PHONE_NUMBER")
    private String phoneNumber;
    @ApiModelProperty(value = "头像", name = "HEADPORTRAIT")
    @TableField(value = "HEADPORTRAIT")
    private String head_portrait;
    @ApiModelProperty(value = "职位", name = "post")
    @TableField(value = "POST")
    private String post;
    @ApiModelProperty(value = "启用状态:1是，0否 ,添加默认为启用", name = "isEnabled", example = "1", allowableValues = "1,0")
    @TableField(value = "IS_ENABLED")
    private Integer isEnabled;
    @ApiModelProperty(value = "更新时间", name = "updateTime")
    @TableField(value = "UPDATE_TIME")
    private String updateTime;
    @TableField(value = "CREATE_TIME")
    private String createTime;
    @ApiModelProperty(value = "删除状态", name = "state",allowableValues = "1,0")
    @TableField(value = "STATE")
    private Integer state;
    @ApiModelProperty(value = "备注", name = "note")
    @TableField(value = "NOTE")
    private String note;
}
