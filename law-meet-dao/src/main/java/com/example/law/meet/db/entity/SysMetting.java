package com.example.law.meet.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys-metting")//预约
public class SysMetting {
    // 预约人id
    String id;
    //预约人姓名
    String  userId;
    //预约地址
    String address;
    // 相关人员
     String participants;
    // 预约日期 时间
    Date strTime;
    //结束时间
    Date  endTime;

//    // 相关人员信息
//    String  aboutName;
//    // 相关人 联系方式
//    String  aboutIphone;
//    //会见人
//    String  interViewUser;
//    //律师资格证
//    String imgUrl;
//    //预约描述
//    String desc;
//    // 预约状态
//    String restatus;

}
