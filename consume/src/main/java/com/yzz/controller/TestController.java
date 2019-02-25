package com.yzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
 public class TestController {
	
	
	@Autowired
    RestTemplate restTemplate;
	
	@GetMapping("/test")
    public void add() {
         restTemplate.getForEntity("http://PRODUCE/test?str=1", String.class);
    }
	
}
