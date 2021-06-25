package com.yuanfenge.stream.service;

public interface DelayService {
    boolean sendDelayMessage(String content, long time);

    boolean sendGeneralMessage(String content);
}
