package com.example.law.meet.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@EnableOAuth2Sso   //开启单点登陆
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class LawMeetClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(LawMeetClientApplication.class, args);
    }

}
