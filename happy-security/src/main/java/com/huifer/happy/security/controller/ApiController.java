package com.huifer.happy.security.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "测试api")
@RestController
public class ApiController {


	@ApiOperation(value = "hello", notes = "返回输入值")
	@GetMapping("/hello")
	public String hello(
			@ApiParam(value = "姓名") @RequestParam("name") String name
	) {
		return name;
	}
}
