package com.example.law.meet.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_cert_approval")
public class SysCertApproval {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer adminId;

    private Integer certId;

    private String approval;

    private Date createTime;

    private Date updateTime;
}
