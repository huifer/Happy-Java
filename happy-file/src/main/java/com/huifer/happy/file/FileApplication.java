package com.huifer.happy.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FileApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(FileApplication.class, args);
    }
}
