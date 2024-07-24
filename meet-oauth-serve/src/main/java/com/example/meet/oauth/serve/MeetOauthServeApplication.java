package com.example.meet.oauth.serve;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.example.law.meet.db.dao"})
public class MeetOauthServeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetOauthServeApplication.class, args);
    }

}
