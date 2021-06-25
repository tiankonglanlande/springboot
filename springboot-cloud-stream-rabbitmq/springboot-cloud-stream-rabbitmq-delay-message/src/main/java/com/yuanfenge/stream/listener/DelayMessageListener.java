package com.yuanfenge.stream.listener;

import com.yuanfenge.stream.delay.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DelayMessageListener {

    @StreamListener(Topic.INPUT)
    public void  doGetDelayMessage(Message<String> message){
        log.info("收到延时消息：{}",message.getPayload());
    }

}
