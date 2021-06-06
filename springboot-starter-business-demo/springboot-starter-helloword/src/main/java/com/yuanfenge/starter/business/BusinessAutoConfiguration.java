package com.yuanfenge.starter.business;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BusinessProperties.class)
public class BusinessAutoConfiguration {
    /**
     * 注册BusinessComponent到spring容器
     * @param properties
     * @return
     */
    @Bean
    public BusinessComponent businessComponent(BusinessProperties properties){
        BusinessComponent businessComponent = new BusinessComponent(properties);
        return businessComponent;
    }
}
