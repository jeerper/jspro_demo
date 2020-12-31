package com.summit.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author: jeerper
 * @since: 2020/12/16 15:49
 */
@Data
@TableName(value = "sys_user")
public class UserModel {
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
    @ApiModelProperty(value = "更新时间", name = "updateTime", hidden = true)
    @TableField(value = "UPDATE_TIME")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime  updateTime;
    @ApiModelProperty(value = "创建时间", name = "createTime", hidden = true)
    @TableField(value = "CREATE_TIME")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")//出参格式化,jackson在序列化时间时是按照国际标准时间GMT进行格式化的，而在国内默认时区使用的是CST时区，两者相差8小时。
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") //入参格式化
    private LocalDateTime createTime;
    @ApiModelProperty(value = "删除状态", name = "state",allowableValues = "1,0")
    @TableField(value = "STATE")
    private Integer state;
    @ApiModelProperty(value = "备注", name = "note")
    @TableField(value = "NOTE")
    private String note;
}
