package com.huifer.happy.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RedisApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(RedisApplication.class, args);

    }
}
