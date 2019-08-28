package com.huifer.happy.restfull.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartController {
	@RequestMapping("/heart")
	public String heart() {
		return "i'm ok!";
	}
}
