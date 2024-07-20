package com.yuanfenge;

import org.springframework.context.annotation.Import;

/**
 * @description: 开启异常重试
 * @author: 猿份哥
 * @date: 2024/7/20
 **/
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Documented
@Import(RetryAutoConfiguration.class)
public @interface EnableRetry {
}
