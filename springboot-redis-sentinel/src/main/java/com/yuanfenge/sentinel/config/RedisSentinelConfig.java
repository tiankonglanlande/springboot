package com.yuanfenge.sentinel.config;

import io.lettuce.core.ReadFrom;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 配置读写分离：从slave节点配置
 * @description:
 * @author: 猿份哥
 * @date: 2023/6/25
 **/
@Configuration
public class RedisSentinelConfig {
    @Bean
    public LettuceClientConfigurationBuilderCustomizer configurationBuilderCustomizer(){
        //优先从replica节点读取，master来兜底
        return clientConfigurationBuilder -> clientConfigurationBuilder.readFrom(ReadFrom.REPLICA_PREFERRED);
    }
}
