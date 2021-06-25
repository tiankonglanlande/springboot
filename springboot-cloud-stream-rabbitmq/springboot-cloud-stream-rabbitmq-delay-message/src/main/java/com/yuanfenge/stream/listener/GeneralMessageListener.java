package com.yuanfenge.stream.listener;

import com.yuanfenge.stream.delay.GeneralTopic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GeneralMessageListener {

    @StreamListener(GeneralTopic.INPUT)
    public void  generalMessage(Message<String> message){
        log.info("收到普通消息：{}",message.getPayload());
    }
}
