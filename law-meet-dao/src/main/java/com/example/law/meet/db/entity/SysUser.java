package com.example.law.meet.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("sys_user")
public class SysUser {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String passWord;

    private Byte gender;

    private Date birthday;

    private LocalDateTime lastLoginTime;

    private String lastLoginIp;

    private String nickname;

    private String mobile;

    private String avatar;

    private String weixinOpenid;

    private String sessionKey;

    private Byte status;

    private LocalDateTime addTime;
    private LocalDateTime updateTime;

}
