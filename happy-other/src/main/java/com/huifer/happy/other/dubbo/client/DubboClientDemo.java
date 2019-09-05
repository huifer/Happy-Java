package com.huifer.happy.other.dubbo.client;

import com.huifer.happy.other.dubbo.provider.DubboHello;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboClientDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "META-INF/spring/dubbo-provider.xml");
        DubboHello dubboHello = (DubboHello) context.getBean("dubboHello");
        dubboHello.hello("hello-dubbo");
    }
}
