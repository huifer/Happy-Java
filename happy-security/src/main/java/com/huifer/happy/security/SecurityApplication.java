package com.huifer.happy.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SecurityApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(SecurityApplication.class, args);
	}
}
