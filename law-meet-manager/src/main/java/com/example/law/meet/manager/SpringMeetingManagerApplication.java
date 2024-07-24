package com.example.law.meet.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.example.law.meet.db.dao"})
public class SpringMeetingManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMeetingManagerApplication.class, args);
    }

}
