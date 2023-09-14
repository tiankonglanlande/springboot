package com.yuanfenge.redisson.single.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class RedissonConfig {
    @Autowired
    RedisProperties redisProperties;
    @Bean
    public RedissonClient redissonClient(){

        String url = String.format("redis://%s:%d", redisProperties.getHost(), redisProperties.getPort());
        // 1. Create config object
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer().setAddress(url);
        if (Objects.nonNull(redisProperties.getPassword())){
            singleServerConfig.setPassword(redisProperties.getPassword());
        }
        //2. 使用json序列化方式
        Codec codec = new JsonJacksonCodec();
        config.setCodec(codec);
        RedissonClient client = Redisson.create(config);
        return client;
    }
}
