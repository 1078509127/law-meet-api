package com.example.law.meet.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_reserve")//预约
public class SysReserve {

    // 预约人id
    @TableId(value = "id", type = IdType.AUTO)
    int id;
    //预约人姓名
    int  user_id;
    // 预约人手机号
    String  phone;
    // 预约看守所
    String  add;
    // 预约日期 时间
    Date  start_time;
    //结束时间
    Date  end_time;
    //会见人
    String  inter_view_user;
    //律师资格证
    String img_url;
    //预约描述
    String remark;
    // 预约状态
    int restatus;
//    // 相关人员信息
//    String  aboutName;
//    // 相关人 联系方式
//    String  aboutIphone;



}
