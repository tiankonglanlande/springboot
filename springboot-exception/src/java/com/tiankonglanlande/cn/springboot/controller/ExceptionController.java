package com.tiankonglanlande.cn.springboot.controller;

import com.tiankonglanlande.cn.springboot.MyException;
import com.tiankonglanlande.cn.springboot.result.Result;
import com.tiankonglanlande.cn.springboot.result.ResultEnum;
import com.tiankonglanlande.cn.springboot.result.ResultUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {
    @RequestMapping("/exception")
    public Result exception(String name,String pwd) throws Exception {
            String realname="tiankonglanlande";
            String realPwd="123";

            if(null!=name&&name.equals("xx")){
                throw new Exception("系统异常！");
            }
            if(StringUtils.isEmpty(name)||StringUtils.isEmpty(pwd)){
                throw new MyException("参数必须传！");
            }else if (!name.equals(realname)||!pwd.equals(realPwd)){
                throw new MyException("用户名或密码不正确！");
            }else if (name.equals("aa")){
                throw new MyException(200,"用户名存在！");
            }
            String info="你好["+name+"]!";
        return ResultUtils.success(info);
    }
    @RequestMapping("/unlogin")
    public Result unlogin() throws Exception {
        return ResultUtils.success(ResultEnum.UNLOGIN);
    }
    @RequestMapping("/success")
    public Result success() throws Exception {
        return ResultUtils.success(200,"自定义消息");
    }
}
