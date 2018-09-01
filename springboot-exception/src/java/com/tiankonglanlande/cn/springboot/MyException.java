package com.tiankonglanlande.cn.springboot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyException extends RuntimeException {
    private Integer code;
    public MyException(String msg){
        super(msg);
    }

    public MyException(Integer code,String msg){
        super(msg);
        this.code=code;
    }
}
