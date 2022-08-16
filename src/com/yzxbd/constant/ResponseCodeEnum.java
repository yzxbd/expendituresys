package com.yzxbd.constant;

public enum ResponseCodeEnum {
    SUCCESS("200","请求成功"),
    FAILURE("500","请求失败");
    private String code;
    private String msg;

    ResponseCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
