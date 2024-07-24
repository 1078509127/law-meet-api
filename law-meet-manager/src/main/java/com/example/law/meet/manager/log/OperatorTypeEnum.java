package com.example.law.meet.manager.log;

public enum OperatorTypeEnum {

    OTHER(0,"其他"),
    MANAGE(1,"后台用户"),
    MOBILE(2,"手机端用户");

    private Integer code;
    private String name;

    OperatorTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}


