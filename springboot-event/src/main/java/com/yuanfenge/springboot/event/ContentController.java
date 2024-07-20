package com.yuanfenge.springboot.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {
    @Autowired
    private ApplicationContext applicationContext;

    /**
     *  http://localhost:8080/event/内容
     * 注意：@PathVariable方式（会发送两次事件，会收到一个xxx.jpg的请求调用）
     * 如果要使用要排除：会收到一个xxx.jpg的请求调用
     * @param content
     * @return
     */
    @GetMapping("/event/{content}")
    public String sendEvent(@PathVariable String content) {
        if (!content.endsWith(".jpg")){//避免其他的调用
            applicationContext.publishEvent(new ContentEvent(this, content));
        }
        return content;
    }

    /**
     * http://localhost:8080/event2?content=%E6%B5%8B%E8%AF%95%E4%BA%8B%E4%BB%B6
     *  注意：推荐
     * @param content
     * @return
     */
    @GetMapping("/event2")
    public String sendEvent2(@RequestParam String content) {
        applicationContext.publishEvent(new ContentEvent(this, content));
        return content;
    }
}
