package com.huifer.happy.security.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "测试api")
@RestController
public class ApiController {


    @Value("${server.port}")
    private int port;

    @ApiOperation(value = "hello", notes = "返回输入值")
    @GetMapping("/hello")
    public String hello(
            @ApiParam(value = "姓名") @RequestParam("name") String name
    ) {
        System.out.println("当前访问接口={}" + port);
        return name;
    }
}
