package com.yuanfenge.exception.advice;

import com.yuanfenge.exception.excption.MyException;
import com.yuanfenge.exception.result.Result;
import com.yuanfenge.exception.result.ResultEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return Result.builder()
                .code(ResultEnum.EXCEPTION.getCode())
                .message(ResultEnum.EXCEPTION.getMsg())
                .build();
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Result myException(HttpServletRequest request, MyException e) {
        e.printStackTrace();
        Integer code = e.getCode();
        String message = e.getMessage();

        if (e.getCode() == null) {
            code = ResultEnum.EXCEPTION.getCode();
        }
        if (e.getMessage() == null) {
            message = ResultEnum.EXCEPTION.getMsg();
        }
        return Result.builder()
                .code(code)
                .message(message)
                .build();
    }
}
