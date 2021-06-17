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
     * 保存重复提交标记 默认为需要保存
     */
    boolean save() default true;

    /**
     * 重复失效时间单位毫秒，默认5000毫秒
     * @return
     */
    long  timeOut() default 5000 ;
}
