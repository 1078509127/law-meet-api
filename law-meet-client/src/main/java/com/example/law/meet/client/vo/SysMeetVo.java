package com.example.law.meet.client.vo;

import lombok.Data;

@Data
public class SysMeetVo {

    private Integer id;

    private Integer userId;

    private String name;

    private String phone;

    private String people;

    private String address;

    private String remark;

    private String startTime;

    private String endTime;

    private Byte status;

    private String approval;

}
