package com.example.law.meet.db.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ColumnWidth(25)
public class SysMeetExample {

    @ExcelIgnore
    private Integer id;
    @ExcelProperty("用户id")
    private Integer userId;
    @ExcelProperty("律师姓名")
    private String name;
    @ExcelProperty("律师电话")
    private String phone;
    @ExcelProperty("会见人")
    private String people;
    @ExcelProperty("会见开始时间")
    private LocalDateTime startTime;
    @ExcelProperty("会见结束时间")
    private LocalDateTime endTime;
    @ExcelProperty("会见地点")
    private String address;
    @ExcelProperty("预约说明")
    private String remark;

    @ExcelIgnore
    private Byte status;
    @ExcelProperty("审核状态")
    private String statusStr;

    @ExcelIgnore
    private LocalDateTime createTime;
    @ExcelIgnore
    private LocalDateTime updateTime;
    @ExcelProperty("审批备注")
    private String approval;

}
