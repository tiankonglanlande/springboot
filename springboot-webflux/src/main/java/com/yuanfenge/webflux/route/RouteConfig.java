package com.yuanfenge.webflux.route;

import com.yuanfenge.webflux.handler.StudentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/29 22:54
 */
@Configuration
public class RouteConfig {
    @Autowired
    private StudentHandler studentHandler;

    @Bean
    public RouterFunction<ServerResponse> stuRouter() {
        return route(GET("/stu"), serverRequest -> studentHandler.getStuSimple(serverRequest));
    }

    @Bean
    public RouterFunction<ServerResponse> listRouter() {
        return route(GET("/students"), serverRequest -> studentHandler.getList(serverRequest));
    }

}
