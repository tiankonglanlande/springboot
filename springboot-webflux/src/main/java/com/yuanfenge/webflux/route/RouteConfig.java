package com.yuanfenge.webflux.route;

import com.yuanfenge.webflux.bean.Student;
import com.yuanfenge.webflux.handler.StudentHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/29 22:54
 */
@Slf4j
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

    /**
     * 直接调用业务
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> usersRouter() {
        //模拟数据
        ArrayList<Student> datas = new ArrayList<>();
        datas.add(new Student("zs", 20));
        datas.add(new Student("ls", 22));
        datas.add(new Student("ww", 28));


        log.info("初始化数据---完成---");

        return route(GET("/users"),
                request -> ok()
                .contentType(MediaType.APPLICATION_JSON)
                        //包装数据
                .body(Mono.just(datas), Student.class)
                        //缓存数据10s
                       // .cache(Duration.ofSeconds(10))
        );
    }
}
