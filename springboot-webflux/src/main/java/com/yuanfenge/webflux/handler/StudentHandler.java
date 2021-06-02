package com.yuanfenge.webflux.handler;

import com.yuanfenge.webflux.bean.Student;
import com.yuanfenge.webflux.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/29 22:57
 */
@Component
public class StudentHandler {
    @Autowired
    private StuService stuService;

    public Mono<ServerResponse> getStuSimple(ServerRequest serverRequest) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(stuService.selectSimple()), Student.class);
    }

    public Mono<ServerResponse> getList(ServerRequest serverRequest) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(Flux.fromIterable(stuService.selectList()), Student.class);
    }
}
