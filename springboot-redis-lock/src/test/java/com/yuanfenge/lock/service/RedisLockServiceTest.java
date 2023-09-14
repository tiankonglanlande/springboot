package com.yuanfenge.lock.service;

import com.yuanfenge.lock.LockFactory;
import com.yuanfenge.lock.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2023/4/9
 **/
@Slf4j
@SpringBootTest
class RedisLockServiceTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Test
    void testReentrantLock() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                ReentrantLock lock=new ReentrantLock();
                try {
                    boolean b = lock.tryLock(30, TimeUnit.SECONDS);
                    if (b){
                        log.info("{}线程获取到锁",Thread.currentThread().getName());
                        Thread.sleep(2000);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }finally {
                    lock.unlock();
                }
            },String.valueOf(i)).start();
        }

        //阻塞主线程：为了看效果
        TimeUnit.SECONDS.sleep(30);
    }

    @Test
    void testRedisLock() throws InterruptedException {
        int size=20;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(size,()->System.out.println("=>任务全部完成"));
        //创建10个线程
        for (int i = 0; i < size; i++) {
            new Thread(()->{

                RedisLock redisLock=new RedisLock(stringRedisTemplate,"user:lock");
                try {

                    boolean b = redisLock.tryLock();
                    if (b){
                        log.info("{}线程获取到锁",Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(15);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }finally {
                    redisLock.unlock();
                }

                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            },String.valueOf(i)).start();
        }

        //为了查看效果主线程等待
        TimeUnit.SECONDS.sleep(200);
    }


    @Test
    void testRedisLock2() throws InterruptedException {
        int size=10;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        //创建10个线程
        for (int i = 0; i < size; i++) {
            new Thread(()->{
                //线程里面获取锁
                RedisLock redisLock=new RedisLock(stringRedisTemplate,"user:lock");
                try {
                    boolean b = redisLock.tryLock();
                    if (b){
                        log.info("{}线程获取到锁",Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(15);
                    }
                    countDownLatch.countDown();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }finally {
                    redisLock.unlock();
                }
            },i+"").start();
        }
        //为了查看效果主线程等待
        countDownLatch.await();
    }

    /**
     * 测试：尝试一定时间获取锁，成功才进行业务。超时则放弃
     * @throws InterruptedException
     */
    @Test
    void testRedisLock3() throws InterruptedException {
        int size=100;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        //创建10个线程
        for (int i = 0; i < size; i++) {
            new Thread(()->{
                //线程里面获取锁
                RedisLock redisLock=new RedisLock(stringRedisTemplate,"user:lock");
                try {
                    //模拟随机生成尝试等待时间
                    Random random = new Random();
                    boolean b = redisLock.tryLock(random.nextInt(60000),TimeUnit.MILLISECONDS);
                    if (b){
                        log.info("{}线程获取到锁",Thread.currentThread().getName());

                        System.out.println("执行业务逻辑！");
                        TimeUnit.SECONDS.sleep(15);
                    }else {
                        log.info("获取锁超时！！！！");
                    }
                    countDownLatch.countDown();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }finally {
                    redisLock.unlock();
                }
            },i+"").start();
        }
        //为了查看效果主线程等待
        countDownLatch.await();
    }

    @Test
    public void testRedisLock4(){
        CompletableFuture one=CompletableFuture.runAsync(()->{
            long tryLockTime=100;
            doItemWork(tryLockTime);
        });
        CompletableFuture tow=CompletableFuture.runAsync(()->{
            long tryLockTime=200;
            doItemWork(tryLockTime);
        });

        CompletableFuture three=CompletableFuture.runAsync(()->{
            long tryLockTime=17000;
            doItemWork(tryLockTime);
        });
        CompletableFuture four=CompletableFuture.runAsync(()->{
            long tryLockTime=340000;
            doItemWork(tryLockTime);
        });

        CompletableFuture.allOf(one,tow,three,four).join();
        log.info("全部执行完成！");
    }

    private void doItemWork(long tryLockTime) {
        RedisLock redisLock=new RedisLock(stringRedisTemplate,"user:lock");
        try {

            //模拟随机生成尝试等待时间
            boolean b = redisLock.tryLock(tryLockTime,TimeUnit.MILLISECONDS);
            if (b){
                log.info("{}线程获取到锁",Thread.currentThread().getName());

                System.out.println("执行业务逻辑！");
                TimeUnit.SECONDS.sleep(10);
            }else {
                log.info("获取锁超时！！！！");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            redisLock.unlock();
        }
    }


    @Test
    public void testRedisLock5(){
        CompletableFuture one=CompletableFuture.runAsync(()->{
            long tryLockTime=100;
            doItemWorkFactory(tryLockTime);
        });
        CompletableFuture tow=CompletableFuture.runAsync(()->{
            long tryLockTime=200;
            doItemWorkFactory(tryLockTime);
        });

        CompletableFuture three=CompletableFuture.runAsync(()->{
            long tryLockTime=17000;
            doItemWorkFactory(tryLockTime);
        });
        CompletableFuture four=CompletableFuture.runAsync(()->{
            long tryLockTime=340000;
            doItemWorkFactory(tryLockTime);
        });

        CompletableFuture.allOf(one,tow,three,four).join();
        log.info("全部执行完成！");
    }

    public void doItemWorkFactory(long tryLockTime){
        Lock redisLock= LockFactory.RedisLock.getLock(stringRedisTemplate,"user:lock");
        try {

            //模拟随机生成尝试等待时间
            boolean b = redisLock.tryLock(tryLockTime,TimeUnit.MILLISECONDS);
            if (b){
                log.info("{}线程获取到锁",Thread.currentThread().getName());

                System.out.println("执行业务逻辑！");
                TimeUnit.SECONDS.sleep(10);
            }else {
                log.info("获取锁超时！！！！");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            redisLock.unlock();
        }
    }
}