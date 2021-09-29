package com.summit.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.summit.dao.entity.FaceModel;
import com.summit.service.FaceService;
import com.summit.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: jeerper
 * @date: 2021-09-29 10:54:52
 */
@RestController
@RequestMapping("/face")
@Transactional(rollbackFor = Exception.class)
public class FaceController {
    private static final Logger logger = LoggerFactory.getLogger(FaceController.class);
    @Autowired
    private FaceService faceService;
    @ApiOperation(value = "上传人脸图片")
    @PostMapping(value = "/insertFace")
    public Object insertFace(@RequestParam("faceImage") MultipartFile faceImage,
                            String faceName,Integer gender) {
        try {
            String faceId= IdWorker.getIdStr();
            FaceModel faceModel = new FaceModel(faceId,faceName,gender);
            faceService.insertFace(faceImage,faceModel);
            return  ResponseUtil.sucessResponse("新增成功");
        } catch (Exception e) {
            logger.error("新增失败",e);
            return ResponseUtil.failedResponse("新增失败", e.getMessage());
        }
    }
}
