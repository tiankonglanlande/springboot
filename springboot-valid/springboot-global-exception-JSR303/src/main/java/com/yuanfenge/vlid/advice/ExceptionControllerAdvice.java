package com.yuanfenge.vlid.advice;

import com.yuanfenge.vlid.exception.ParamException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yuanfenge
 * 方法1：异常处理
 */
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 已废弃：发现此方法已失效
     * 使用{@link ExceptionControllerAdvice#bindExceptionHandler(MethodArgumentNotValidException)}替代
     * 处理BindException异常
     * 获得参数和错误消息封装到map集合通过json返回
     * @param ex
     * @return
     * @throws Exception
     */
    @Deprecated
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Map<String, Object> bindExceptionHandler(BindException ex) throws Exception {
        String s = ex.getFieldError().getField() + ":" + ex.getFieldError().getDefaultMessage();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 400);
        map.put("msg", s);
        return map;
    }

    /**
     * 最新参数绑定异常
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, Object> bindExceptionHandler(MethodArgumentNotValidException ex) throws Exception {
        BindingResult result = ex.getBindingResult();
        StringBuilder s=new StringBuilder();
        if (result.hasErrors()) {

            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p ->{
                FieldError fieldError = (FieldError) p;
                s.append(fieldError.getField()+":"+fieldError.getDefaultMessage());
            });
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",400);
        map.put("msg",s);
        return map;
    }
    /**
     * 处理自定义的ParamException异常
     * 获得参数和错误消息封装到map集合通过json返回
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(ParamException.class)
    @ResponseBody
    public Map<String, Object> customExceptionHandler(ParamException ex) throws Exception {
        String msg = ex.getMessage();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 400);
        map.put("msg", msg);
        return map;
    }

    /**
     * 处理其他异常
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler(Exception ex) throws Exception {
        String msg = ex.getMessage();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", msg);
        return map;
    }
}
