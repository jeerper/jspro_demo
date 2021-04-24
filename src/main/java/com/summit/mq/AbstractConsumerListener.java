package com.summit.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Configuration
public abstract class AbstractConsumerListener implements MessageListenerConcurrently {
    private Logger logger= LoggerFactory.getLogger(AbstractConsumerListener.class);
    @Autowired
    private  MqConfig config;
    @Autowired
    private ObjectMapper objectMapper;
    protected DefaultMQPushConsumer consumer;
    private String topic;
    private String tags;
    private ConsumeFromWhere consumeFromWhere;
    public AbstractConsumerListener() {
        this("platform","paltform",ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
    }
    public AbstractConsumerListener(String topic, String tags) {
        this(topic,tags,ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
    }
    public AbstractConsumerListener(String topic, String tags, ConsumeFromWhere consumeFromWhere) {
        this.consumeFromWhere = consumeFromWhere;
        this.topic = topic;
        this.tags = tags;
    }

    //public abstract void handlerMessage(Object data, MessageExt message);

    public abstract void handlerMessage(MessageExt msg);

    @PostConstruct
    public void start(){
        this.consumer = new DefaultMQPushConsumer(this.config.getConsumerGroup());
        if (this.config.isEnable()){
            try{
                this.consumer.setNamesrvAddr(this.config.getNameService());
                this.consumer.setConsumeFromWhere(this.consumeFromWhere);
                this.consumer.subscribe(this.topic,"*");
                this.consumer.registerMessageListener(this);
                this.consumer.setConsumeMessageBatchMaxSize(Integer.valueOf(this.config.getBatchMessageSize()));
                this.consumer.start();
            }catch (MQClientException e){
                logger.error("MQ:消费者启动失败：{}-{}",e.getErrorMessage(),e.getResponseCode());
            }
        }
    }
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context){
        int index=0;
        ConsumeConcurrentlyStatus concurrentlyContext;
        try{
            while (index<msgs.size()){
                MessageExt msg = msgs.get(index);
                String[] tagList = this.tags.split("\\|\\|");
                String[] tags=tagList;
                int tagSize=tags.length;
                for (int j=0;j<tagSize;j++){
                    String tag=tags[j];
                    if (tag.equals(msg.getTags())){
                        try{
                            this.handlerMessage(msg);
                        }catch (Exception e){
                            logger.error("MQ:消费者接收消息失败");
                        }
                        logger.info("MQ:消费者接受消息：{} {} {} {} {}",new Object[]{msg.getMsgId(),msg.getTopic(),msg.getTags(),new String(msg.getBody(),"utf-8")});
                    }
                }
                index++;
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            concurrentlyContext=ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }finally {
            if (index<msgs.size()){
                context.setAckIndex(index+1);
            }
        }
        return concurrentlyContext;

    }

    @PreDestroy
    public void stop(){
        if (this.consumer!=null){
            this.consumer.shutdown();
        }
        logger.info("MQ: 关闭生产者");
    }
}
