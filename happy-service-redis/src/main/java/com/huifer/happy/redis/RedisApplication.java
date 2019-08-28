package com.huifer.happy.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RedisApplication {
	@Autowired
	private RedisTemplate redisTemplate;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(RedisApplication.class, args);

	}
}
