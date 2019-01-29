package com.tiankonglanlande.cn.springboot.springbootasync.service;

import com.tiankonglanlande.cn.springboot.springbootasync.bean.Coffee;
import com.tiankonglanlande.cn.springboot.springbootasync.task.TaskOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/29 16:10
 */
@Service
public class AsyncTaskService {
    @Autowired
    private TaskOne taskOne;

    public List<Coffee> getCoffeeList(){
        List<Coffee> results= null;
        results = new ArrayList<>();
        Coffee coffeeSimple=taskOne.getCoffee();
        List<Coffee> coffeeList=taskOne.getCoffees();

        results.add(coffeeSimple);
        results.addAll(results.size(),coffeeList);
        return results;
    }

    public List<Coffee> getAsyncCoffeeList(){
        List<Coffee> results= null;
        try {
            results = new ArrayList<>();
            CompletableFuture<Coffee> coffeeSimpleFuture = taskOne.asyncCoffeeSimple();
            CompletableFuture<List<Coffee>> coffeeListFuture = taskOne.asyncCoffeeList();
            CompletableFuture.allOf(coffeeSimpleFuture,coffeeListFuture).join();

            Coffee coffeeSimple=coffeeSimpleFuture.get();
            List<Coffee> coffeeList=coffeeListFuture.get();

            results.add(coffeeSimple);
            results.addAll(results.size(),coffeeList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return results;
    }
}
