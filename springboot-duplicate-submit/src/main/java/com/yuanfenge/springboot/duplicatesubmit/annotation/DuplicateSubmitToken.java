package com.yuanfenge.springboot.duplicatesubmit.annotation;

import java.lang.annotation.*;

/**
 * @author 猿份哥
 * @description 防止表单重复提交注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DuplicateSubmitToken {
    /**
     * 一次请求完成之前防止重复提交
     */
    public static final int REQUEST = 1;
    /**
     * 一次会话中防止重复提交
     */
    public static final int SESSION = 2;

    /**
     * 保存重复提交标记 默认为需要保存
     */
    boolean save() default true;

    /**
     * 防止重复提交类型，默认：一次请求完成之前防止重复提交
     */
    int type() default REQUEST;
}
