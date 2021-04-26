package com.summit.mq;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MqConsumer extends AbstractConsumerListener<MqConsumer.Data>{
    private Logger logger= LoggerFactory.getLogger(MqConsumer.class);
    public static class Data{
        private String id;
        private String dataInfo;
        public Data() {
        }

        public Data(String id, String dataInfo) {
            this.id = id;
            this.dataInfo = dataInfo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDataInfo() {
            return dataInfo;
        }

        public void setDataInfo(String dataInfo) {
            this.dataInfo = dataInfo;
        }
    }
    MqConsumer(){
        super("jsproTest","testTag");
    }

    @Override
    public void handlerMessage(Data data, MessageExt message) {
        logger.info("MQ接收到的消息为自定义：" + JSON.toJSONString(data));
    }

    @Override
    public void handlerMessage(MessageExt msg) {

    }
}
