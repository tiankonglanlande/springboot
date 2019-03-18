package com.example.vlid.demo.vliddemo.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyErrorController{

    /**
     * 处理404
     * @param request
     * @param response
     * @return
     */
    @RequestMapping
    public Object error(HttpServletRequest request,HttpServletResponse response){

        String uri=request.getRequestURI();
        Map<String,Object> map=new HashMap<>();
        map.put("code",404);
        map.put("msg","not found page"+uri);

        return map;
    }
}