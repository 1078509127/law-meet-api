package com.example.law.meet.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 认证中心
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_cert")
public class SysCert {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String name;

    private String phone;

    private Byte gender;

    private String image;

    private String idCard;

    private String wkCard;

    private String city;

    private LocalDateTime baDate;

    private String wkImage;

    private Byte status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Byte deleted;
}
