package com.yuanfenge.exception.advice;

import com.yuanfenge.exception.excption.DuplicateSubmitException;
import com.yuanfenge.exception.excption.MyException;
import com.yuanfenge.exception.result.Result;
import com.yuanfenge.exception.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@Slf4j
@ControllerAdvice
public class MyExceptionAdvice {

    /**
     * 其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultException( Exception e) {
        log.error("系统发生异常：{}",e);
        return Result.builder()
                .code(ResultEnum.EXCEPTION.getCode())
                .message(ResultEnum.EXCEPTION.getMsg())
                .build();
    }

    /**
     * 重复请求异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = DuplicateSubmitException.class)
    @ResponseBody
    public Result duplicateSubmitException(DuplicateSubmitException e) {
        e.printStackTrace();
        return Result.builder()
                .code(ResultEnum.DUPLICATE_EXCEPTION.getCode())
                .message(ResultEnum.DUPLICATE_EXCEPTION.getMsg())
                .build();
    }

    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Result myException(MyException e) {
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
