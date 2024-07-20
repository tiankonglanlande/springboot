package com.yuanfenge.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2024/7/18
 **/
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(public * com.yuanfenge.aop.controller..*.*(..))")
    public void weblog(){}

    @Before("weblog()")
    public void before(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println("before 执行>>>");
        parameterMap.forEach((k,v)-> System.out.println(k+" = "+Arrays.toString(v)));
        String requestURI = request.getRequestURI();
        System.out.println("requestURI="+requestURI);
        String requestURL = request.getRequestURL().toString();
        System.out.println("requestURL="+requestURL);
        int serverPort = request.getServerPort();
        System.out.println("serverPort="+serverPort);
        String contextPath = request.getContextPath();
        System.out.println("contextPath="+contextPath);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            System.out.println(name + " "+request.getHeader(name));
        }
    }

    @Around("weblog()")
    public  Object  around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around 执行start>>>");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("around 执行end<<<");
        return proceed;
    }


    @After("weblog()")
    public void after(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println("after 执行>>>");
        parameterMap.forEach((k,v)-> System.out.println(k+" = "+Arrays.toString(v)));

    }
    @AfterReturning("weblog()")
    public void afterReturning(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println("afterReturning 执行>>>");
        parameterMap.forEach((k,v)-> System.out.println(k+" = "+Arrays.toString(v)));
    }
    @AfterThrowing("weblog()")
    public void afterThrowing(){
        System.out.println("afterThrowing执行>>>");

    }
}
