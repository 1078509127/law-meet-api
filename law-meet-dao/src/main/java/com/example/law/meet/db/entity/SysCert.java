package com.example.law.meet.db.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.law.meet.db.util.ImageConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.time.LocalDateTime;

/**
 * 认证中心
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_cert")
@ContentRowHeight(55)
public class SysCert {

    @TableId(value = "id", type = IdType.AUTO)
    @ExcelIgnore
    private Integer id;

    @ExcelProperty("用户id")
    private Integer userId;

    @ExcelProperty("用户名")
    private String name;

    @ExcelProperty("手机号码")
    private String phone;

    @ExcelProperty("性别")
    private Byte gender;

    @ExcelProperty(value = {"最新照片"},converter = ImageConverter.class)
    @ColumnWidth(35)
    private String image;

    @ExcelProperty("身份证号")
    private String idCard;

    @ExcelProperty("职业证号")
    private String wkCard;

    @ExcelProperty("所在城市")
    private String city;

    @ExcelProperty("备案日期")
    @ColumnWidth(25)
    private LocalDateTime baDate;

    @ExcelProperty(value = {"职业证书"},converter = ImageConverter.class)
    @ColumnWidth(35)
    private String wkImage;

    @ExcelProperty("审核状态")
    private Byte status;

    @ExcelIgnore
    private LocalDateTime createTime;
    @ExcelIgnore
    private LocalDateTime updateTime;
    @ExcelIgnore
    private Byte deleted;
}
