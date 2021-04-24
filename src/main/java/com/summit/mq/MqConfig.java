package com.summit.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.URL;


@Configuration
@ConfigurationProperties("mq")
public class MqConfig {
    private Logger logger= LoggerFactory.getLogger(MqConfig.class);
    private static final String PREFIX="MQ";
    private boolean enable=false;
    private String nameService = "127.0.0.1:9876";
    private String producerGroup;
    private String consumerGroup;
    private String batchMessageSize="1";
    private int maxMessageSize=4194304;

    public MqConfig() {
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public static String getPREFIX() {
        return PREFIX;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        try {
            this.nameService = nameService;
//            URL url = new URL(nameService);
//            this.nameService = url.getAuthority();
        }catch (Exception e){
            this.logger.error("nameService请求地址不正确",e);
        }
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public String getBatchMessageSize() {
        return batchMessageSize;
    }

    public void setBatchMessageSize(String batchMessageSize) {
        this.batchMessageSize = batchMessageSize;
    }

    public int getMaxMessageSize() {
        return maxMessageSize;
    }

    public void setMaxMessageSize(int maxMessageSize) {
        this.maxMessageSize = maxMessageSize;
    }
}
