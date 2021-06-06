package com.yuanfenge.stream.send.service;


import com.yuanfenge.stream.common.BeanVO;

public interface ISendService {
    boolean send(BeanVO content);
    boolean send(String content);
}
