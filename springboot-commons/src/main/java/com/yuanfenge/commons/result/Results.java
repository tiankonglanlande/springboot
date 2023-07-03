package com.yuanfenge.commons.result;

/**
 * 结果工具类
 */
public class Results {
    public static Result success(Object data) {
        return Result.builder()
                .code(ResultEnum.SUCCESS.getCode())
                .message(ResultEnum.SUCCESS.getMsg())
                .data(data)
                .build();
    }

    public static Result success(Integer code, String msg) {
        if (null == msg) msg = ResultEnum.SUCCESS.getMsg();
        if (null == code) code = ResultEnum.SUCCESS.getCode();
        return Result.builder()
                .code(code)
                .message(msg)
                .build();
    }

    public static Result success(ResultEnum resultEnum) {
        return success(resultEnum.getCode(), resultEnum.getMsg());
    }

    public static Result fail(String msg) {
        if (null == msg) msg = ResultEnum.FAIL.getMsg();
        return Result.builder()
                .code(ResultEnum.FAIL.getCode())
                .message(msg)
                .build();
    }

    public static Result fail(Integer code, String msg) {
        if (null == msg) msg = ResultEnum.FAIL.getMsg();
        if (null == code) code = ResultEnum.FAIL.getCode();
        return Result.builder()
                .code(code)
                .message(msg)
                .build();
    }

    public static Result fail() {
        return fail(ResultEnum.FAIL);
    }

    public static Result fail(ResultEnum resultEnum) {
        return fail(resultEnum.getCode(), resultEnum.getMsg());
    }
}
