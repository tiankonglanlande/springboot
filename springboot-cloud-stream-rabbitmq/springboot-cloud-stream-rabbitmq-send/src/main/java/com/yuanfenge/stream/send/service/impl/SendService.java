package com.yuanfenge.stream.send.service.impl;

import com.yuanfenge.stream.common.BeanVO;
import com.yuanfenge.stream.send.service.ISendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
@EnableBinding(Source.class)
public class SendService implements ISendService {
    @Autowired
    private Source source;

    /**
     * 发消息
     * @return
     */
    @Override
    public boolean send(BeanVO vo) {
        log.info("发送方：发出{}消息",vo.toString());
        //source.output().send(MessageBuilder.withPayload(vo).build(),1000); //设置超时
        return source.output().send(MessageBuilder.withPayload(vo).build());
    }
    @Override
    public boolean send(String vo) {
        log.info("发送方：发出{}消息",vo.toString());
        //source.output().send(MessageBuilder.withPayload(vo).build(),1000); //设置超时
        return source.output().send(MessageBuilder.withPayload(vo).build());
    }
}
