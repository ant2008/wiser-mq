package com.wiser.mqclient;

import com.wiser.mq.service.ConsumeMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


@ComponentScan(basePackages = {"com.wiser"})
//@EnableJpaRepositories(basePackages = {"com.wiser"})
//@EntityScan(basePackages = {"com.wiser"})
//@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
//		DataSourceTransactionManagerAutoConfiguration.class, DataSourceProperties.class})
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class MqClientApplication {

	@Autowired
	private ConsumeMqService consumeMqService;

	public static void main(String[] args) {
		SpringApplication.run(MqClientApplication.class, args);
	}

	@Bean
	public ConsumeMqService consumeMqService()
	{
		try
		{
			consumeMqService.consumeMq();
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return consumeMqService;
	}




}