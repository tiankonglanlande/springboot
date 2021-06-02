package com.yuanfenge.webflux.route;

import com.yuanfenge.webflux.handler.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author 猿份哥
 * @description
 * @createTime 2019/2/24 11:53
 */
@Configuration
public class StudentRouter {

    @Bean
    public RouterFunction<ServerResponse> routeStu(StudentHandler studentHandler) {
        return route(GET("/routeStu"), serverRequest -> studentHandler.getStuSimple(serverRequest));
    }

    @Bean
    RouterFunction<ServerResponse> routList(StudentHandler studentHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/routeList")
                .and(RequestPredicates
                        .contentType(MediaType.TEXT_PLAIN)), studentHandler::getList);
    }
}
