package com.huifer.happy.other.dubbo;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboProviderDemo {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "META-INF/spring/dubbo-provider.xml");
        context.start();
        System.in.read();
    }
}
