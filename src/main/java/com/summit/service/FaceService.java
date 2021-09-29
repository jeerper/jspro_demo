package com.summit.service;

import com.summit.dao.entity.FaceModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: jeerper
 * @date: 2021-09-29 11:12:10
 */
public interface FaceService {

    /**
     * 上传人脸图片
     * @param faceModel
     * @throws IOException
     */
    void insertFace(MultipartFile faceImage,FaceModel faceModel) throws IOException;
}
