package com.yuanfenge.valid.group.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.*;

/**
 * @author 猿份哥
 * @description 自定义StatusConstraintValidator implements ConstraintValidator<自定义注解类,要校验的参数类型>
 */
public class StatusConstraintValidator implements ConstraintValidator<Status,Integer> {
    Set<Integer> set=new HashSet<>();

    /**
     * 初始化时将数据添加到集合中
     * @param annotation
     */
    @Override
    public void initialize(Status annotation) {
        int[] values = annotation.values();
        for (int v:values) {
            if (!set.contains(v)){
                set.add(v);
            }
        }
    }

    /**
     * 在此方法进行校验
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // TODO 校验业务代码
        //如果值符合校验通过
        return set.contains(value);
    }
}
