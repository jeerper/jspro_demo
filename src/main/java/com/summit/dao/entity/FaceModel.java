package com.summit.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author: jeerper
 * @date: 2021-09-29 11:01:42
 */
@TableName(value = "sys_face")
public class FaceModel {
    @ApiModelProperty(name = "faceId",value = "人脸id")
    @TableField(value = "faceId")
    private String faceId;
    @ApiModelProperty(name = "faceName",value = "人脸名称")
    @TableField(value = "faceName")
    private String faceName;
    @ApiModelProperty(name = "faceImage",value = "人脸图片")
    @TableField(exist = false)
    private MultipartFile faceImage;
    @ApiModelProperty(name = "facePath",value = "人脸路径")
    @TableField(value = "facePath")
    private String facePath;
    @ApiModelProperty(name = "gender",value = "性别")
    @TableField(value = "gender")
    private Integer gender;
    @ApiModelProperty(name = "gmtCreate",value = "创建时间")
    @TableField(value = "gmtCreate")
    private Date gmtCreate;

    public FaceModel() {
    }

    public FaceModel(String faceId, String faceName, Integer gender) {
        this.faceId = faceId;
        this.faceName = faceName;
        this.gender = gender;
    }

    public String getFacePath() {
        return facePath;
    }

    public void setFacePath(String facePath) {
        this.facePath = facePath;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public String getFaceName() {
        return faceName;
    }

    public void setFaceName(String faceName) {
        this.faceName = faceName;
    }

    public MultipartFile getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(MultipartFile faceImage) {
        this.faceImage = faceImage;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
