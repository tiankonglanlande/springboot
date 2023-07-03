package com.yuanfenge.commons.result;

public enum ResultEnum {
    SUCCESS(200, "成功"),
    FAIL(210, "失败"),
    NOT_LOGIN_IN(220, "未登录"),
    DUPLICATE_EXCEPTION(230, "请不要频繁操作！"),
    PARAMS_EXCEPTION(300,"缺少参数！"),
    EXCEPTION(500, "系统异常，请稍后再试！");


    private Integer code;
    private String msg;

    private ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
