package com.example.law.meet.manager.log;

/**
 * 业务操作类型
 */
public enum BusinessTypeEnum {

    OTHER(0,"其他"),
    INSERT(1,"新增"),
    UPDATE(2,"修改"),
    DELETE(3,"删除"),
    ASSGIN(4,"授权"),
    EXPORT(5,"导出"),
    IMPORT(6,"导入"),
    FORCE(7,"强退"),
    STATUS(8,"更新状态"),
    CLEAN(9,"清空数据"),
    PASS(10,"通过"),
    REJECT(11,"拒绝"),
    ;

    private Integer code;
    private String name;

    BusinessTypeEnum(Integer code, String name) {
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

