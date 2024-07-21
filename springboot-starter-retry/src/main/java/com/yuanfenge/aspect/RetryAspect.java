package com.yuanfenge.aspect;

import com.yuanfenge.annotion.Retry;
import com.yuanfenge.properties.RetryProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @description: 重试Aspect
 * @author: 猿份哥
 * @date: 2024/7/20
 **/
@Slf4j
@Aspect
@Component
public class RetryAspect {
    @Autowired
    RetryProperties properties;

    @Pointcut("@annotation(com.yuanfenge.annotion.Retry)")
    public void retryPoint(){}

    @Around("retryPoint()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //获取方法的注解的参数
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Exception exception=null;
        if (method.isAnnotationPresent(Retry.class)){
            Retry annotation = method.getAnnotation(Retry.class);
            //设置初始值：用户注解配置 > yml配置 > 初始值
            int maxRetryCount = annotation.maxRetryCount();
            if (maxRetryCount==Retry.DEFAULT_MAX_RETRY_VAL){
                maxRetryCount= properties.maxRetryCount;
            }
            Class<? extends Exception>[] exceptions = annotation.exceptions();
            if (exceptions.length== 1){
                Class<? extends Exception> es = exceptions[0];
                if (Retry.DefaultEmptyClass.class.getName().equals(es.getName())) {
                    exceptions = properties.getClasses();
                    if (null == exceptions) {
                        exceptions = new Class[1];
                        exceptions[0] = Retry.INIT_CLASS;
                    }
                }
            }

            String fallback = annotation.fallback();
            if (fallback.equals(Retry.DEFAULT_FALLBACK)){
                fallback= properties.fallback;
            }
            long time = annotation.time();
            if (time==Retry.DEFAULT_TIME){
                time= properties.time;
            }

            int count=0;
            for (int i = 0; i < maxRetryCount; i++) {
                try {
                    Object proceed = point.proceed();
                    return proceed;
                } catch (Exception e) {
                    //e.printStackTrace();
                    exception=e;
                    //抛出的异常不在指定的异常里则不进行重试
                    if (!containsException(exceptions,exception)){
                        log.info("不再配置的异常内终止重试！");
                        break;
                    }
                } finally {
                }
                Thread.sleep(time);
                log.info("进行异常重试第"+(count+1)+"次");
                count++;
            }
            //重试次数达到最大上限则回调fallback
            if (count>0 && maxRetryCount==count){
                return execute(point.getThis(),fallback);
            }
        }

        //将异常传递出去
        throw new Exception(exception);
    }

    /**
     * 有配置fallback则执行配置的fallback，否则执行默认的fallback
     *
     * @param aThis
     * @param fallback
     * @return
     */
    private Object execute(Object aThis, String fallback) {
        if (StringUtils.hasLength(fallback)){
            return invokeFallback(aThis, fallback);
        }
        return defaultFallback();
    }

    private Object invokeFallback(Object target, String fallbackName) {
        // 这里使用反射来调用fallback方法
        // 注意：这个实现很基础，没有处理方法参数或返回值类型不匹配的情况
        try {
            Method fallbackMethod = target.getClass().getDeclaredMethod(fallbackName);
            fallbackMethod.setAccessible(true);
            Object invoke = fallbackMethod.invoke(target);
            return invoke;
        } catch (Exception e) {
            //throw new RuntimeException(e);
            log.error("没有找到对应的"+fallbackName+"方法，调用默认的defaultFallback");
            //没有找到相应的fallback则调用默认的defaultFallback方法
            return defaultFallback();
        }
    }

    private boolean containsException(Class<? extends Exception>[] exceptions, Exception e) {
        for (int i = 0; i < exceptions.length; i++) {
           if (exceptions[i].isInstance(e)){
               return true;
           }
        }
        return false;
    }

    private Object defaultFallback() {
        return "服务器出错，请稍后再试！";
    }
}
