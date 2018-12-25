package com.tiankonglanlande.cn.springboot.duplicatesubmit.exception;

/**
 * @author 猿份哥
 * 自定义异常
 */
public class DuplicateSubmitException extends Exception {

    public DuplicateSubmitException(String msg){
        super(msg);
    }
}
