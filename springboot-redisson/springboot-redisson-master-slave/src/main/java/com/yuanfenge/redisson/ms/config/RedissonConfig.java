package com.yuanfenge.redisson.ms.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.MasterSlaveServersConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * cluster使用
 * @author 猿份哥
 */
@Configuration
public class RedissonConfig {
    @Autowired
    RedisProperties redisProperties;

    @Bean
    public RedissonClient redissonClient() {

        //1. 组装url
        //List<String> nodes = redisProperties.getSentinel()//哨兵模式
        List<String> nodes = redisProperties.getCluster()//集群模式
                .getNodes()
                .stream()
                .map(i -> "redis://" + i)
                .collect(Collectors.toList());
        // 2. Create config object
        Config config = new Config();
        MasterSlaveServersConfig masterSlaveConfig = config.useMasterSlaveServers();
        masterSlaveConfig.setMasterAddress(nodes.get(0))
                .addSlaveAddress(nodes.get(1))
                .addSlaveAddress(nodes.get(2))
                .setPassword(redisProperties.getPassword());

        //3. 使用json序列化方式
        Codec codec = new JsonJacksonCodec();
        config.setCodec(codec);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
