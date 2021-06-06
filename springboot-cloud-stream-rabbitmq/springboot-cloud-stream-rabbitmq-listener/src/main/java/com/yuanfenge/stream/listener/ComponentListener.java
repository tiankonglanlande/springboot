package com.yuanfenge.stream.listener;

import com.yuanfenge.stream.common.BeanVO;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Processor.class)
public class ComponentListener {
    @StreamListener(Sink.INPUT)
    public void listener(Message<BeanVO> message){
        //BeanVO payload = message.getPayload();
        System.out.printf("接收方：接收到消息{},开始提供服务！！！",message.toString());
    }
}
