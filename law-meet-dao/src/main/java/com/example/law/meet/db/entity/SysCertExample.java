package com.example.law.meet.db.entity;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.example.law.meet.db.util.ImageConverter;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ContentRowHeight(55)
@ColumnWidth(25)
public class SysCertExample {

    @ExcelIgnore
    private Integer id;
    @ExcelProperty("用户id")
    private Integer userId;
    @ExcelProperty("用户名")
    private String name;
    @ExcelProperty("手机号码")
    private String phone;

    @ExcelIgnore
    private Byte gender;
    @ExcelProperty("性别")
    private String sex;

    @ExcelProperty(value = {"最新照片"},converter = ImageConverter.class)
    private String image;
    @ExcelProperty("身份证号")
    private String idCard;
    @ExcelProperty("职业证号")
    private String wkCard;
    @ExcelProperty("所在城市")
    private String city;
    @ExcelProperty("执证发放日期")
    private LocalDateTime basDate;
    @ExcelProperty("执证截至日期")
    private LocalDateTime baeDate;
    @ExcelProperty(value = {"职业证书"},converter = ImageConverter.class)
    private String wkImage;

    @ExcelIgnore
    private Byte status;
    @ExcelProperty("审核状态")
    private String statusStr;

    @ExcelIgnore
    private LocalDateTime createTime;
    @ExcelIgnore
    private LocalDateTime updateTime;
    @ExcelIgnore
    private Byte deleted;

    @ExcelProperty("审批备注")
    private String approval;
}
