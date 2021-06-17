package com.yuanfenge.springboot.duplicatesubmit.controller;

import com.yuanfenge.springboot.duplicatesubmit.annotation.DuplicateSubmitToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author 猿份哥
 * @description
 */
@RestController
public class TestController {

    @DuplicateSubmitToken
    @RequestMapping(value = "/restful/{num}", method = RequestMethod.GET)
    public Map<String, Object> restful(@PathVariable(value = "num") int num) throws Exception {
        Map<String, Object> map=new HashMap<>();
        if (num == 2) { //手动抛个异常
            throw new Exception("====system exception haha !===");
        }
        map.put("welcome","hello word !");
        map.put("method","restful：num="+num);
        return map;
    }

    @DuplicateSubmitToken
    @RequestMapping(value = "/getParam", method = RequestMethod.GET)
    public Map<String, Object> getParam(@RequestParam(value = "num") int num) throws Exception {
        Map<String, Object> map=new HashMap<>();
        if (num == 2) {
            throw new Exception("====system exception haha !===");
        }
        map.put("welcome","hello word !");
        map.put("method","get带参数：num="+num);
        return map;
    }

    @DuplicateSubmitToken
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Map<String, Object> get() throws Exception {
        Map<String, Object> map=new HashMap<>();
        map.put("welcome","hello word !");
        map.put("method","get无参");
        return map;
    }

    /**
     * post请求方式
     * 设置30秒内不允许重复请求
     * @param num
     * @return
     * @throws Exception
     */
    @DuplicateSubmitToken(timeOut = 30*1000)
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public Map<String, Object> post(@RequestParam(value = "num") int num) throws Exception {
        Map<String, Object> map=new HashMap<>();
        map.put("welcome","hello word !");
        map.put("method","post：num="+num);
        return map;
    }
}