package com.yuanfenge.redis;

import com.yuanfenge.redis.service.RedisPipelineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * @author 猿份哥
 * @description
 * @createTime 2019/8/01 20:35
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisPipelineServiceTest {

	@Autowired
	private RedisPipelineService pipelineService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	private String pattern="yuanfenge:test:";


	@Test
	public void batchGet() throws Exception {
		batchInsert();
		redisPipelineGet();
		//generalBatchGet();
		redisStringBatchGet();
	}

	public void batchInsert() throws Exception {
		long stime=System.currentTimeMillis();
		List<Map<String, String>> saveList=new ArrayList<Map<String, String>>();
		for (int i = 0; i < 10000; i++) {
			Map<String,String> map=new HashMap<>();
			map.put("key",pattern+i);
			map.put("value","value值为"+i);
			saveList.add(map);
		}
		pipelineService.batchInsert(saveList, TimeUnit.MINUTES,15);

		long etime=System.currentTimeMillis();
		System.out.println("插入10000条数据消耗时间为："+(etime-stime));

	}

	private void redisPipelineGet() {
		long stime=System.currentTimeMillis();
		Set<String> keys = stringRedisTemplate.keys(pattern + "*");
		List<String> keyList=new ArrayList<>();
		keys.forEach(i->{
			keyList.add(i);
		});
		List<String> strings = pipelineService.batchGet(keyList);
		long etime=System.currentTimeMillis();
		System.out.println("string="+strings);
		System.out.println("使用Pipelined消耗时间为："+(etime-stime));
	}


	public void redisStringBatchGet() {
		long stime=System.currentTimeMillis();
		Set<String> keys = stringRedisTemplate.keys(pattern + "*");
		List<String> keyList=new ArrayList<>();
		keys.forEach(i->{
			keyList.add(i);
		});
		List<String> strings = stringRedisTemplate.opsForValue().multiGet(keyList);
		long etime=System.currentTimeMillis();
		System.out.println("string="+strings);
		System.out.println("使用multiGet消耗时间为："+(etime-stime));
	}

	public void generalBatchGet() {
		long stime=System.currentTimeMillis();
		Set<String> keys = stringRedisTemplate.keys(pattern + "*");
		List<String> keyList=new ArrayList<>();
		keys.forEach(i->{
			keyList.add(i);
		});
		List<String> strings=new ArrayList<>();
		for (String key : keyList) {
			String value = stringRedisTemplate.opsForValue().get(key);
			strings.add(value);
		}
		long etime=System.currentTimeMillis();
		System.out.println("string="+strings);
		System.out.println("消耗时间为："+(etime-stime));
	}

}