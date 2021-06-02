package com.yuanfenge.vlid.controller;

import com.yuanfenge.vlid.exception.ParamException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 方法2：异常处理 其他controller继承BaseController即可
 */
public class BaseController {
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Map<String, Object> bindExceptionHandler(BindException ex) throws Exception {
        String s = ex.getFieldError().getField() + ":" + ex.getFieldError().getDefaultMessage();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 400);
        map.put("msg", s);
        return map;
    }

    @ExceptionHandler(ParamException.class)
    @ResponseBody
    public Map<String, Object> customExceptionHandler(ParamException ex) throws Exception {
        String msg = ex.getMessage();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 400);
        map.put("msg", msg);
        return map;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> customExceptionHandler(Exception ex) throws Exception {
        String msg = ex.getMessage();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", msg);
        return map;
    }

}
