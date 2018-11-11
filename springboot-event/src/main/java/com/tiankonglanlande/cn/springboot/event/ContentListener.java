package com.tiankonglanlande.cn.springboot.event;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ContentListener{

    @Async
    @EventListener
    public void handler(ContentEvent event){
        log.info("收到消息"+event.getContent());
    }
}
