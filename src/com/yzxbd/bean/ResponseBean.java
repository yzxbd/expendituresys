package com.yzxbd.bean;

import com.yzxbd.constant.ResponseCodeEnum;

public class ResponseBean<T> {
    /**
     * 状态码
     */
    private String code;
    /**
     * 描述信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    public ResponseBean() {
    }

    public ResponseBean(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseBean(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseBean success(){
        return new ResponseBean(ResponseCodeEnum.SUCCESS.getCode(),ResponseCodeEnum.SUCCESS.getMsg());
    }

    public static <T> ResponseBean  success(T data){
        return new ResponseBean(ResponseCodeEnum.SUCCESS.getCode(),ResponseCodeEnum.SUCCESS.getMsg(),data);
    }

    public static ResponseBean failure(){
        return new ResponseBean(ResponseCodeEnum.FAILURE.getCode(),ResponseCodeEnum.FAILURE.getMsg());
    }


    @Override
    public String toString() {
        return "ResponseBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
