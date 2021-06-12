package com.yuanfenge.loading;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class LoadingConfigApp {
    public static void main(String[] args) {
        SpringApplication.run(LoadingConfigApp.class, args);
    }

    /**
     * 此处代码已废弃，在boot2.5.0版本已无效使用MyEnvironmentPostProcessor替代
      */
    @Deprecated
    @Bean
    public static PropertySourcesPlaceholderConfigurer loadProperties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("external-application.yml"));
        configurer.setProperties(yaml.getObject());
        return configurer;
    }

}