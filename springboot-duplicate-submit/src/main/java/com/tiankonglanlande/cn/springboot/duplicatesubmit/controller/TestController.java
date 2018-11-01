package com.tiankonglanlande.cn.springboot.duplicatesubmit.controller;

import com.tiankonglanlande.cn.springboot.duplicatesubmit.annotation.DuplicateSubmitToken;
import com.tiankonglanlande.cn.springboot.duplicatesubmit.exception.DuplicateSubmitException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author 天空蓝蓝的
 * @description
 */
@RestController
public class TestController {

    @DuplicateSubmitToken(type = DuplicateSubmitToken.SESSION)
    @RequestMapping(value = "/test/d", method = RequestMethod.GET)
    public Map<String, Object> test (HttpServletRequest request) throws Exception {

        /*Random r=new Random();
        int i = r.nextInt(3);
        if (i==2){
            throw new DuplicateSubmitException("=======duplicate submit exception=====");
        }*/

        try {
            Random r=new Random();
            int i = r.nextInt(3);
            if (i==2){
                throw new Exception("====system exception haha !===");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("request Url", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }

}