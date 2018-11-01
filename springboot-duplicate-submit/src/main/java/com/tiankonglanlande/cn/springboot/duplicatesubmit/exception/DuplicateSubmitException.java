package com.tiankonglanlande.cn.springboot.duplicatesubmit.exception;

/**
 * @author 天空蓝蓝的
 * 自定义异常
 */
public class DuplicateSubmitException extends Exception {

    public DuplicateSubmitException(String msg){
        super(msg);
    }
}
