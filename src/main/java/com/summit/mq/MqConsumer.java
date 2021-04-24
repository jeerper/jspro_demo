package com.summit.mq;

import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MqConsumer extends AbstractConsumerListener{
    private Logger logger= LoggerFactory.getLogger(MqConsumer.class);
    @Override
    public void handlerMessage(MessageExt msg) {
        logger.info("MQ接收到的消息为：" + msg.toString());
    }
}
