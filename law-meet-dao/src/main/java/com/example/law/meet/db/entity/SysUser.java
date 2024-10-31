package com.example.law.meet.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_user")
public class SysUser {

    private int id;
    private String userName;

    private String passWord;

    private String weixinOpenid;

    private String avatar;

    private String nickname;

    private Byte gender;

    private Byte status;

    private LocalDateTime lastLoginTime;

    private String lastLoginIp;

    private String sessionKey;
}
