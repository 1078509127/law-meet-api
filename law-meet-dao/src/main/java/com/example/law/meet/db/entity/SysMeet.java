package com.example.law.meet.db.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 会见、预约
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_meet")
public class SysMeet {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String name;

    private String phone;

    private String people;

    private String address;

    private String remark;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Byte status;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
