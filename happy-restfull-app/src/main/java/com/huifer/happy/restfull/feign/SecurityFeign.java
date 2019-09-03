package com.huifer.happy.restfull.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "happy-security",value = "happy-security")
public interface SecurityFeign {

	@GetMapping("/hello")
	String hello(@RequestParam("name") String name);

}
