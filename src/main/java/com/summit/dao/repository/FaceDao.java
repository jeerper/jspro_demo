package com.summit.dao.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.summit.dao.entity.FaceModel;
import com.summit.dao.entity.UserModel;


/**
 * @author: jeerper
 * @date: 2021-09-29 11:38:26
 */
public interface FaceDao  extends BaseMapper<FaceModel> {

    void insertFace(FaceModel faceModel);
}
