package com.huifer.happy.restfull.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "test-work1",name = "test-work1")
public interface Feign {
	@RequestMapping(value = "/work1")
	String work1();
}
