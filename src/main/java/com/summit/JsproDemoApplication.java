package com.summit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.summit.dao")
@ServletComponentScan
@EnableTransactionManagement
public class JsproDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsproDemoApplication.class, args);
    }

}
