package com.summit.mq;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.summit.model.DeviceData;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MqConsumer extends AbstractConsumerListener<DeviceData>{
    private Logger logger= LoggerFactory.getLogger(MqConsumer.class);
    @Autowired
    private ObjectMapper objectMapper;

    MqConsumer(){
        super("jsproTest","testTag");
    }
    @Override
    public void handlerMessage(DeviceData deviceData, MessageExt message) {
        logger.info("MQ接收到的消息为自定义：" + JSON.toJSONString(deviceData));
    }
    @Override
    public void handlerMessage(MessageExt msg) {
        List<String> data = new ArrayList<>();
        data.add("qq");

        try {
            DeviceData deviceData = this.objectMapper.readValue(new String(msg.getBody(), "utf-8"), DeviceData.class);
            logger.info("MQ接收到的消息为自定义：" + JSON.toJSONString(deviceData));
        } catch (Exception e) {
            logger.error("json解析失败收到的数据======>"+e);
        }
    }
}
