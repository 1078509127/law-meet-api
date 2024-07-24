package com.example.law.meet.client.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxReserveInfo {
    // 预约人id
    int id;
    //预约人姓名
    int  userId;
    // 预约人手机号
    String  phone;
    // 预约看守所
    String  reAdd;
    // 预约日期 时间
    String  strTime;
    //结束时间
    String endTime;

    // 相关人员信息
    String  aboutName;
    // 相关人 联系方式
    String  aboutIphone;
    //会见人
    String  interViewUser;
    //律师资格证
    String imgUrl;
    //预约描述
    String desc;
    // 预约状态
    String status;
    // 天
    String dataDay;


}
