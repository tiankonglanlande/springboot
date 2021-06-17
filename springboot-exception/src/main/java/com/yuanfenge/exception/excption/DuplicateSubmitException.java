package com.yuanfenge.exception.excption;

/**
 * @author 猿份哥
 * 自定义异常
 */
public class DuplicateSubmitException extends MyException {

    public DuplicateSubmitException(String msg) {
        super(msg);
    }
}
