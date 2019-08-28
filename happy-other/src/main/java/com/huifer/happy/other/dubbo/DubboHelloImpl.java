package com.huifer.happy.other.dubbo;

import com.huifer.happy.other.dubbo.provider.DubboHello;

public class DubboHelloImpl implements DubboHello {

    @Override
    public String hello(String msg) {
        return "dubbo : " + msg;
    }

}
