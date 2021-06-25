package com.yuanfenge.stream.service;

import com.yuanfenge.stream.delay.GeneralTopic;
import com.yuanfenge.stream.delay.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding({
        Topic.class,
        GeneralTopic.class
})
public class DelayServiceImpl implements DelayService {

    @Autowired
    private Topic topic;

    @Autowired
    private GeneralTopic generalTopic;

    @Override
    public boolean sendDelayMessage(String content, long time){
        String msg = String.format("延时【%d】毫秒，内容：%s", time, content);
        return  topic.output().send(MessageBuilder.withPayload(msg)
                .setHeader("x-delay",time).build());
    }

    @Override
    public boolean sendGeneralMessage(String content) {
        return generalTopic.output().send(MessageBuilder.withPayload(content).build());
    }
}
