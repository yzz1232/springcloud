package com.yzz.service.impl;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yzz.service.TestService;

@Service
public class TestServiceImpl implements TestService{

	@Override
	@HystrixCommand(fallbackMethod = "addServiceFallback")
	public String test() {
		
	   int i = 2/0;
		
		return "success";
		
	}
	
	
	public String addServiceFallback(){
		return "error";
	}
	
	
	
}
