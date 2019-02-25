package com.yzz.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.ttl.TransmittableThreadLocal;

public class StudyTransmittableThreadLocal {
	
	public static void main(String[] args) {
		TransmittableThreadLocal<String> context = new TransmittableThreadLocal<String>();
		//TestThreadLocal.set("test");
		context.set("test");
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		Runnable run = ()->{
			System.out.println(context.get());
		
		};

		executorService.execute(run);
		executorService.shutdown();
	}
	
}
