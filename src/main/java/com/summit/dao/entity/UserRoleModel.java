package com.summit.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: jeerper
 * @since: 2020/12/26 17:29
 */
@Data
@TableName(value = "sys_user")
public class UserRoleModel {
    @ApiModelProperty(value = "ID", name = "ID", required = true)
    @TableField(value = "ID")
    private String id;
    @ApiModelProperty(value = "USERNAME", name = "用户名")
    @TableField(value = "USERNAME")
    private String userName;
    @ApiModelProperty(value = "角色code", name = "roleCode", allowableValues = "1,2")
    @TableField(value = "ROLE_CODE")
    private String roleCode;

}
