package com.example.law.meet.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_feedback")
public class SysFeedback {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String type;

    private String phone;

    private String content;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
