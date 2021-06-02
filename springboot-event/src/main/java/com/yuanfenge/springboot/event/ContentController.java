package com.yuanfenge.springboot.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ContentController {
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/event/{content}")
    public void sendEvent(@PathVariable String content) {
        applicationContext.publishEvent(new ContentEvent(this, content));
    }
}
