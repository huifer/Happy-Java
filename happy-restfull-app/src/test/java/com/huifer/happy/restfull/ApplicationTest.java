package com.huifer.happy.restfull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import javax.validation.executable.ValidateOnExecution;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTest {

	@Autowired
	private RestTemplate restTemplate;


	@Test
	public void testRest() {
		ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:9000/heart", String.class);
		System.out.println();
	}

}