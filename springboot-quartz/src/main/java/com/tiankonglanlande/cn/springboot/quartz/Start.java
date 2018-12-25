package com.tiankonglanlande.cn.springboot.quartz;

import com.tiankonglanlande.cn.springboot.quartz.factory.MyJobFactory;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author 猿份哥
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Configuration
public class Start {

	public static void main(String[] args) {
		SpringApplication.run(Start.class, args);
	}

	@Autowired
	private MyJobFactory myJobFactory;

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setJobFactory(myJobFactory);
		System.out.println("myJobFactory:"+myJobFactory);
		return schedulerFactoryBean;
	}
	@Bean
	public Scheduler scheduler() {
		return schedulerFactoryBean().getScheduler();
	}
}
