package com.yuanfenge.commons.utils;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 不带-的UUID
     * @return
     */
    public static String randomUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
