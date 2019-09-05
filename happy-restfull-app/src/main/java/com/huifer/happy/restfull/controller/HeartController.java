package com.huifer.happy.restfull.controller;

import com.huifer.happy.restfull.feign.Feign;
import com.huifer.happy.restfull.feign.SecurityFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HeartController {
    @Autowired
    private SecurityFeign securityFeign;
    @Autowired
    private Feign feign;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/heart")
    public String heart() {
        return "i'm ok!";
    }

    @GetMapping("/hello")
    public String hello(
            @RequestParam("name") String name
    ) {
        String zhangsan = securityFeign.hello(name);
        return zhangsan;
    }

    @GetMapping("/2")
    public void h2() {
        String s = feign.work1();
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://test-work1/work1", String.class);
        System.out.println();
    }
}
