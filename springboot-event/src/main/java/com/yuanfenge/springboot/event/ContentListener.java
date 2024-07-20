package com.yuanfenge.springboot.event;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ContentListener {

    @EventListener
    public void handler(ContentEvent event) {
        log.info("收到消息: " + event.getContent());
    }

}
