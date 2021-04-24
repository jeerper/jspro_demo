package com.summit.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class MqProducer {
    private Logger logger= LoggerFactory.getLogger(MqProducer.class);
    @Autowired
    private MqConfig mqConfig;
    @Autowired
    private ObjectMapper objectMapper;
    DefaultMQProducer producer;

    public MqProducer() {

    }
    @PostConstruct
    public  void  start(){
        this.producer= new DefaultMQProducer(this.mqConfig.getProducerGroup());
        if (this.mqConfig.isEnable()){
            try{
                logger.info("MQ: 启动生产者");
                this.producer.setNamesrvAddr(this.mqConfig.getNameService());
                this.producer.setMaxMessageSize(this.mqConfig.getMaxMessageSize());
                this.producer.start();
            }catch (MQClientException e){
                logger.error("启动生产者失败：{}--{}",e.getResponseCode(),e.getErrorMessage(),e);
            }
        }
    }

    public void sendMessage(Object data, String topic, String tags,String keys){
        try{
            String jsonData = this.objectMapper.writeValueAsString(data);
            byte[] messageBody = jsonData.getBytes("UTF-8");
            Message message = new Message(topic, tags, keys, messageBody);
            this.producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    logger.info("MQ:生产者发生信息成功:{}",sendResult);
                    logger.info("MQ:生产者发生信息成功");
                    System.out.println("MQ:生产者发生信息成功");
                }
                @Override
                public void onException(Throwable throwable) {
                    logger.info("MQ:生产者发生信息失败：",throwable);
                }
            });
        }catch (Exception e){
            logger.error("MQ:生产者发生消息异常：{}",e.getMessage(),e);
        }
    }

    public void  sendData(Object data){
        this.sendMessage(data,"jsproTest","testTag",null);
    }

    @PreDestroy
    public void stop(){
        if (this.producer!=null){
            this.producer.shutdown();
        }
        logger.info("MQ: 关闭生产者");
    }
}
