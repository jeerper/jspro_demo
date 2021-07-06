package com.summit.config;

import com.summit.model.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: jeerper
 * @date: 2021-07-01 15:40:47
 */
@Configuration
public class MyStudentConfig {
    @Bean
    public Student getStudent(){
        return new Student();
    }
}

