package com.example.law.meet.db.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_reserve_message")
public class SysReserveMessage {

    private Integer id;

    private Integer reserveId;

    private Integer isView;
}
