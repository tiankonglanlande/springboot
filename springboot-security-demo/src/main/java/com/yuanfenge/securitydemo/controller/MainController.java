package com.yuanfenge.securitydemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuanfenge
 */
@Slf4j
@Controller
public class MainController {

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping({"/","/index"})
    public String root(Model model) {
        log.info("=========> 请求地址是：/index");
        getUserInfo(model);
        return "index";
    }

    /**
     * 获取用户信息
     * @param model
     */
    public void getUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        /*获取用户信息*/
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                String password = userDetails.getPassword();
                // TODO 可以获取用户的其他信息


                model.addAttribute("userDetails",userDetails);
            }
        }
    }

    /**
     * 用户中心页
     * @param model
     * @return
     */
    @RequestMapping("/user/index")
    public String userIndex(Model model) {
        log.info("=========> 请求地址是：/user/index");
        getUserInfo(model);

        return "user/index";
    }

    /**
     * 自定义的登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        log.info("=========> 请求地址是：自定义的登录页面/login");
        return "login";
    }

    /**
     * 错误后跳转到登录页面
     * @param model
     * @return
     */
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        log.info("=========> 请求地址是：/login-error");
        model.addAttribute("登录错误请重新登录！", true);
        return "login";
    }

}