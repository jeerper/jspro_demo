package com.summit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.summit.dao")
public class JsproDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsproDemoApplication.class, args);
    }

}
