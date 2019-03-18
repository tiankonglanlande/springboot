package com.example.vlid.demo.vliddemo.controller;

import com.example.vlid.demo.vliddemo.bean.Bean;
import com.example.vlid.demo.vliddemo.exception.CustomException;
import com.example.vlid.demo.vliddemo.exception.ParamException;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestContorller {

    @RequestMapping("/valid")
    public Object valid(@Valid Bean bean) throws ParamException, CustomException {
        customParamCheck(bean);
        //customException();
        runtimeException();
        return bean;
    }

    private void runtimeException() {
        int a=1/0;
    }

    private void customParamCheck(@Valid Bean bean) throws ParamException {
        if (bean.getAge()>100){
            throw new ParamException("age不能超过100");
        }
    }

    private void customException() throws CustomException {
        throw new CustomException("customException!!!");
    }


}
