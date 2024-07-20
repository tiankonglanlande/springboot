package com.yuanfenge.properties;

import com.yuanfenge.annotion.Retry;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 异常重试配置
 * @author: 猿份哥
 * @date: 2024/7/20
 **/
@Component
@ConfigurationProperties(prefix = "yuanfenge.retry")
@Data
public class RetryProperties {
    /**
     * 最大异常重试次数 默认3次
     */
    public int maxRetryCount= Retry.INIT_MAX_RETRY_VAL;
    /**
     * 尝试失败maxRetryCount次后指定回调方法默认是defaultFallback方法
     */
    public String fallback = Retry.INIT_FALLBACK;
    /**
     * 每次重复的间隔时间: 默认50毫秒
     */
    public long time = Retry.INIT_TIME;

    /**
     * 指定的异常类名集合
     */
    private List<String> classNames;

    public Class[] getClasses() {
        if (null==classNames){
            return null;
        }
        Class[] classes = new Class[classNames.size()];
        for (int i = 0; i < classNames.size(); i++) {
            try {
                classes[i] = Class.forName(classNames.get(i));
            } catch (ClassNotFoundException e) {
                // Handle exception
            }
        }
        return classes;
    }
}
