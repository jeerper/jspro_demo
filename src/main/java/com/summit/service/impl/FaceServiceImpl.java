package com.summit.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.SystemUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.summit.controller.FaceController;
import com.summit.dao.entity.FaceModel;
import com.summit.dao.repository.FaceDao;
import com.summit.service.FaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: jeerper
 * @date: 2021-09-29 11:12:24
 */
@Service
public class FaceServiceImpl implements FaceService {
    private static final Logger logger = LoggerFactory.getLogger(FaceServiceImpl.class);
    //图片存放根路径
    @Value("${file.rootPath}")
    private String ROOT_PATH;
    //图片存放根目录下的子目录
    @Value("${file.sonPath}")
    private String SON_PATH;

    @Value("${server.port}")
    //获取主机端口
    private String POST;

    @Autowired
    private FaceDao faceDao;
    @Override
    public void insertFace(MultipartFile faceImage,FaceModel faceModel) throws IOException {
        if (!ObjectUtils.isEmpty(faceImage)){
            byte[] faceFileByteArray = faceImage.getBytes();
            //扩展名
            String extName = FileUtil.extName(faceImage.getOriginalFilename());
            String picId = IdWorker.getIdStr();
            String fileName = picId + StrUtil.DOT + extName; // 新文件名
            String fileAbsolutePath = ROOT_PATH  + SON_PATH + fileName;
            String fileRelativePath = SON_PATH + fileName;
            String faceRelativePath = getFacePath(fileRelativePath);
            faceModel.setFacePath(faceRelativePath);
            FileUtil.writeBytes(faceFileByteArray, fileAbsolutePath);
        }
        faceDao.insertFace(faceModel);
    }
    private String getFacePath(String fileRelativePath){
        String host = null;
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("get server host Exception e:", e);
        }
        return  host + ":" + POST + fileRelativePath;
    }
}
