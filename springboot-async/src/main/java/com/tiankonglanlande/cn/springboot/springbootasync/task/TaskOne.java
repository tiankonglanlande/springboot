package com.tiankonglanlande.cn.springboot.springbootasync.task;

import com.tiankonglanlande.cn.springboot.springbootasync.bean.Coffee;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/14 22:56
 */
@Component
public class TaskOne {
    @Async
    public CompletableFuture<Coffee> asyncCoffeeSimple(){
        Coffee coffee = getCoffee();
        return CompletableFuture.completedFuture(coffee);

    }

    public Coffee getCoffee() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Coffee.builder()
                .id(1)
                .name("black coffee")
                .build();
    }

    @Async
    public CompletableFuture<List<Coffee>> asyncCoffeeList(){
        List<Coffee> datas = getCoffees();
        return CompletableFuture.completedFuture(datas);

    }

    public List<Coffee> getCoffees() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Coffee black=Coffee.builder()
                .id(5)
                .name("黑咖啡")
                .build();
        Coffee coffee=Coffee.builder()
                .id(6)
                .name("白咖啡")
                .build();
        List<Coffee> datas=new ArrayList<>();
        datas.add(black);
        datas.add(coffee);
        return datas;
    }
}
