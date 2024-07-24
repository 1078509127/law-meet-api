package com.example.law.meet.common.utils;

import lombok.Getter;

@Getter
public enum StareEnums {

    SUBMIT(0,"已提交"),
    UN_SUBMIT(1,"取消提交"),
    APPROVED(2,"通过"),
    UN_APPROVED(3,"未通过"),

    VIEW(0,"未查看"),
    UN_VIEW(1,"已查看");


    private Integer code;
    private String msg;
    StareEnums(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
