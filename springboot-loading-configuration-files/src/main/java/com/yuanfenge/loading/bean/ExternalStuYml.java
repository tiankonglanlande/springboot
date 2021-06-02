package com.yuanfenge.loading.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "external")
public class ExternalStuYml implements Serializable {

    private String name;
    private String age;
    private Boolean fromShenSheng;
    @Value("${color}")
    private String color;

    private List<String> petList;
    private List<String> hobbyList;

    /**
     * 身份
     */
    private Map<String, Object> identity;
    /**
     * 计划安排清单
     **/
    private Map<String, Object> plainList;

}
