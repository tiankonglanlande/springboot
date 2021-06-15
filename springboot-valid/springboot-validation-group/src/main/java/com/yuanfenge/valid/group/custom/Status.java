package com.yuanfenge.valid.group.custom;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { StatusConstraintValidator.class})
public @interface Status {
    //指定错误默认提示信息
    String message() default "{com.yuanfenge.valid.group.custom.Status.error}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    int [] values() default { };
}
