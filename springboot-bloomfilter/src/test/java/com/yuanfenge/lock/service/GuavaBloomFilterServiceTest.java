package com.yuanfenge.lock.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2023/4/8
 **/
@SpringBootTest
class GuavaBloomFilterServiceTest {

    @Autowired
    private GuavaBloomFilterService guavaBloomFilterService;
    @Test
    void guavaBoolFilter() {

        guavaBloomFilterService.guavaBoolFilter();
    }
}