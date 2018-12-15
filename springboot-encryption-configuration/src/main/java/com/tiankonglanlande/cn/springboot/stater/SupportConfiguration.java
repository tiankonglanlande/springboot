package com.tiankonglanlande.cn.springboot.stater;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySources;


/**
 * @author tiankonglanlande
 * @description
 * @createTime 2018 - 11 - 20 11:53
 */
@EnableEncryptableProperties
@EncryptablePropertySources({@EncryptablePropertySource(value = "classpath:support/application.properties", ignoreResourceNotFound = true),
        @EncryptablePropertySource(value = "classpath:support/application-${spring.profiles.active}.properties", ignoreResourceNotFound = true)})
public class SupportConfiguration {

}
