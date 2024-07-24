package com.example.law.meet.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class SysUser {

    private int id;
    private String userName;
    private String passWord;
    private String wxOpenId;
}
