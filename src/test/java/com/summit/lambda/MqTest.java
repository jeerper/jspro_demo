package com.summit.lambda;

import com.summit.model.Student;
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
    @Autowired
    private Student student;
    @Test
    public void testMq(){
        System.out.println("student------>"+student);
        //mqProducer.sendData("hahahahahahahahahahaha1111111");
    }
}
