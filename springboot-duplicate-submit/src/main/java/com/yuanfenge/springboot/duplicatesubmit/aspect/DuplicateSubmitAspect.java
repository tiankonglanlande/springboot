package com.yuanfenge.springboot.duplicatesubmit.aspect;

import com.yuanfenge.commons.utils.UUIDUtil;
import com.yuanfenge.springboot.duplicatesubmit.annotation.DuplicateSubmitToken;
import com.yuanfenge.springboot.duplicatesubmit.constant.TextConstants;
import com.yuanfenge.exception.excption.DuplicateSubmitException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author 猿份哥
 * @description 防止表单重复提交拦截器
 */
@Aspect
@Component
@Slf4j
public class DuplicateSubmitAspect {
    public static final String DUPLICATE_TOKEN_KEY = "duplicate_token_key";

    @Pointcut("execution(public * com.yuanfenge.springboot.duplicatesubmit.controller..*(..))")

    public void webLog() {
    }

    @Before("webLog() && @annotation(token)")
    public void before(final JoinPoint joinPoint, DuplicateSubmitToken token) throws DuplicateSubmitException {
        if (token != null) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            boolean isSaveSession = token.save();
            if (isSaveSession) {
                String key = getDuplicateTokenKey(joinPoint);
                Object t = request.getSession().getAttribute(key);
                if (null == t) {
                    createKey(request, key);
                } else if (valid(t,token.timeOut())){
                    throw new DuplicateSubmitException(TextConstants.REQUEST_REPEAT);
                } else {
                    createKey(request, key);
                }
            }

        }
    }

    private void createKey(HttpServletRequest request, String key) {
        String uuid = UUIDUtil.randomUUID();
        long now = System.currentTimeMillis();
        String value = uuid + "_" + now;
        request.getSession().setAttribute(key, value);
        log.info("token-key={};token-value={}",key, value);
    }

    /**
     * 是否超时
     * @param t
     * @return
     */
    private boolean valid(Object t, long timeOut) {
        String token = t.toString();
        String[] arr = token.split("_");
        long before = Long.parseLong(arr[1]);
        long now = System.currentTimeMillis();
        if (now-before<timeOut){
            return true;
        }
        return false;
    }

    /**
     * 获取重复提交key
     * @param joinPoint
     * @return
     */
    public String getDuplicateTokenKey(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.asList(joinPoint.getArgs()).stream().map(i -> String.valueOf(i)).collect(Collectors.joining());
        StringBuilder key = new StringBuilder(DUPLICATE_TOKEN_KEY);
        key.append("_").append(methodName).append(args);
        return key.toString();
    }

    /**
     * 异常
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "webLog()&& @annotation(token)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e, DuplicateSubmitToken token) {
        if (null != token
                && e instanceof DuplicateSubmitException == false) {
            //处理重复提交本身之外的异常
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            boolean isSaveSession = token.save();
            //获得方法名称
            if (isSaveSession) {
                String key = getDuplicateTokenKey(joinPoint);
                Object t = request.getSession().getAttribute(key);
                if (null != t) {
                    //方法执行完毕移除请求重复标记
                    request.getSession(false).removeAttribute(key);
                    log.info("异常情况--移除标记！");
                }
            }
        }
    }
}