package com.yuanfenge.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 使用hset实现
 *
 * @description: 实现redis锁
 * @author: 猿份哥
 * @date: 2023/4/9
 **/
@Slf4j
public class RedisLock implements Lock {

    private String lockName;//KEYS[1]
    private String uuid;//ARGV[1]  UUID+线程ID
    private long expireTime; //ARGV[2]
    private final long DEFAULT_EXPIRE_TIME = 60; //默认失效时间60
    private boolean success=false;//获取锁成功

    private StringRedisTemplate stringRedisTemplate;
    private ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    public RedisLock(StringRedisTemplate redisTemplate, String lockName) {
        this.stringRedisTemplate = redisTemplate;
        this.lockName = lockName;
        this.uuid = UUID.randomUUID().toString().replace("-", "") + ":" + Thread.currentThread().getId();
        expireTime = DEFAULT_EXPIRE_TIME;//默认失效时间
    }

    @Override
    public void lock() {
        tryLock();
    }

    @Override
    public void lockInterruptibly() {

    }

    @Override
    public boolean tryLock() {
        return tryLock(-1, TimeUnit.SECONDS);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        try {
            //setNX无法解决锁可重入，使用hset解决锁的可重入问题
            String lockScript =
                    "if redis.call('exists',KEYS[1]) == 0 or redis.call('hexists',KEYS[1],ARGV[1]) == 1 then" +
                            " redis.call('hincrby',KEYS[1],ARGV[1],1) " +
                            " redis.call('expire',KEYS[1],ARGV[2]) " +
                            " return 1 " +
                            "else " +
                            "return 0 " +
                            "end";
            long beginTime = System.currentTimeMillis();
            long targetTime = unit.toMillis(time);
            //循环的条件是：1.用户没有输入时间（time=-1）并且没有获取到锁   2.未超过用户指定尝试时间并且没有获取到锁

            while ((-1 == time || System.currentTimeMillis() - beginTime <= targetTime)
                    && !(success=stringRedisTemplate.execute(new DefaultRedisScript<>(lockScript, Boolean.class), Arrays.asList(lockName), uuid, String.valueOf(expireTime)))) {
                //没有抢到锁，休眠100毫秒再试
                TimeUnit.MICROSECONDS.sleep(100);
            }
            if (success){
                //创建一个线程执行续期操作（延迟1/3的时间进行续期）,
                scheduledThreadPool.schedule(() -> {
                    String script = "if redis.call('hexists',KEYS[1],ARGV[1]) == 1 then   " +
                            "return redis.call('expire',KEYS[1],ARGV[2])   " +
                            "else return 0 " +
                            "end";
                    boolean flag = stringRedisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), Arrays.asList(lockName), uuid, String.valueOf(expireTime));
                    if (flag) {
                        log.info("续期成功");
                    }
                }, expireTime / 3, TimeUnit.SECONDS);

                return true;
            }else {
                return false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void unlock() {
        if (success){
            //不存在返回nil(空)。存在-1(因为可能加锁了几次)，减到等于0的时候删除。
            String script =
                    "if redis.call('hexists',KEYS[1],ARGV[1]) == 0 then " +
                            "return nil " +
                            "elseif redis.call('hincrby',KEYS[1],ARGV[1],-1) == 0 then " +
                            "return redis.call('del',KEYS[1])  " +
                            "else return 0 " +
                            "end";

            Long execute = stringRedisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Arrays.asList(lockName), uuid);
            if (null == execute) {
                throw new RuntimeException("this lock doesn't exists o(╥﹏╥)o ！");
            }
            log.info("{}线程 释放锁成功", Thread.currentThread().getName());
        }

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
