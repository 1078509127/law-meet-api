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

    private LocalDateTime basDate;

    private LocalDateTime baeDate;

    private String wkImage;

    private Byte status;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Byte deleted;
}
