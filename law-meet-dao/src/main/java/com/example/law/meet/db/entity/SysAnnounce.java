package com.example.law.meet.db.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_announce")
public class SysAnnounce {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String type;

    private String content;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
