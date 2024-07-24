package com.example.law.meet.client.utils;

import lombok.Getter;

@Getter
public enum StareEnums {

    SUBMIT(0,"已提交"),
    UN_SUBMIT(1,"取消提交"),
    PASS(2,"通过"),
    UN_PASS(3,"未通过");

    private Integer code;
    private String msg;
    StareEnums(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
