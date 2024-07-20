package com.yuanfenge;

import com.yuanfenge.aspect.RetryAspect;
import com.yuanfenge.properties.RetryProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @description: 异常重试自动配置类
 * @author: 猿份哥
 * @date: 2024/7/20
 **/
@Configuration
@Import({RetryAspect.class,RetryAspect.class, RetryProperties.class})
public class RetryAutoConfiguration {

}
