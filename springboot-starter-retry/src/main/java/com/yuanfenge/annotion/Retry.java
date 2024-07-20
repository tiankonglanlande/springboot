package com.yuanfenge.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 异常重试注解
 * @author: 猿份哥
 * @date: 2024/7/20
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {

    int INIT_MAX_RETRY_VAL=3;
    String INIT_FALLBACK="";
    int INIT_TIME=50;
    Class INIT_CLASS=Exception.class;


    int DEFAULT_MAX_RETRY_VAL=-1;
    String DEFAULT_FALLBACK="-1";
    int DEFAULT_TIME=-1;


    /**
     * 最大尝试次数
     * @return
     */
    int maxRetryCount() default DEFAULT_MAX_RETRY_VAL;

    /**
     * 尝试失败maxRetryCount次后指定回调方法
     * @return
     */
    String fallback() default DEFAULT_FALLBACK;

    /**
     * 指定可以重试的异常类
     * @return
     */
    Class<? extends Exception>[] exceptions() default DefaultEmptyClass.class;
    /**
     * 每次重复的间隔时间: 默认50毫秒
     */
    long time() default DEFAULT_TIME;

    class DefaultEmptyClass extends Exception{

    }
}
