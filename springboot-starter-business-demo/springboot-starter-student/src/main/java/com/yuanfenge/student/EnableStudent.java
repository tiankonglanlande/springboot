package com.yuanfenge.student;

import org.springframework.context.annotation.Import;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2023/9/14
 **/
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Documented
@Import(StudentAutoConfiguration.class)
public @interface EnableStudent {
}
