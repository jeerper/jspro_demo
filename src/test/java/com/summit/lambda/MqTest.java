package com.summit.lambda;

import com.summit.mq.MqProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MqTest {
    @Autowired
    private MqProducer mqProducer;
    @Test
    public void testMq(){
        System.out.println("qqq");
        mqProducer.sendData("111");
    }
}
