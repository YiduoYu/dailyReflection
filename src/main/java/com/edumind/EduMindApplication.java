package com.edumind;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.edumind.mapper")
public class EduMindApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduMindApplication.class, args);
        System.out.println("Congrats😄! EduMind system has started successfully!");
    }
}
