package com.example.law.meet.db.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysMeetExample {

    private Integer id;

    private Integer userId;

    private String name;

    private String phone;

    private String people;

    private String address;

    private String remark;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Byte status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String approval;

}
