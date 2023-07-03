package com.yuanfenge.springboot.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
@ToString
public class ContentEvent<T> extends ApplicationEvent {

    /**
     * 数据
     */
    private T content;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ContentEvent(Object source) {
        super(source);
    }

    public ContentEvent(Object source, T content) {
        super(source);
        this.content = content;
    }
}

