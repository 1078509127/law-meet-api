package com.example.law.meet.common.utils;

public enum ResultCodeEnum {
    // 自定义结果编码和结果信息。
    SUCCESS(200,"成功"),
    FAIL(400,"失败"),
    SERVICE_ERROR(401, "服务异常"),
    DATA_ERROR(402, "数据异常"),
    ILLEGAL_REQUEST(403, "非法请求"),
    REPEAT_SUBMIT(404, "重复提交"),
    LOGIN_AUTH(405, "未登陆"),
    PERMISSION(406, "没有权限"),
    // 自定义...
    ;

    // 结果编码。
    private Integer resultCode;

    // 结果信息。
    private String resultMsg;

    public Integer getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }



    ResultCodeEnum(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
