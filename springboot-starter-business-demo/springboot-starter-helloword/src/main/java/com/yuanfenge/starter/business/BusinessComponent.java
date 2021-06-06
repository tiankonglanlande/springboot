package com.yuanfenge.starter.business;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 实现一个连接集合的功能
 */
public class BusinessComponent {
    BusinessProperties properties;

    public BusinessComponent(BusinessProperties properties) {
        this.properties=properties;
    }

    public String join(){
        String result=null;
        if (null!=properties){
            List<String> list = properties.list;
            if (!CollectionUtils.isEmpty(list)){
                result = list.stream().collect(Collectors.joining(properties.delimiter));
                return result;
            }
        }
        return null;
    }
}
