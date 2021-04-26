package com.summit.controller;

import com.summit.dao.entity.UserModel;
import com.summit.model.DeviceData;
import com.summit.mq.MqProducer;
import com.summit.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: jeerper
 * @since: 2021/4/25 17:47
 */
@RestController
@RequestMapping("/mq")
public class RocketMqController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private MqProducer mqProducer;

    @ApiOperation(value = "mq发送消息")
    @RequestMapping(value = "/sendMessge",method = RequestMethod.GET)
    public void sendMessage(@RequestBody DeviceData data){
        try {
            mqProducer.sendData(data);
        } catch (Exception e) {
            logger.error("发送消息失败",e);
        }
    }
}
