package com.yzz.mq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service("mqMessageConsumer")
@RabbitListener(queues = "fd.mq.message", containerFactory = "firstFactory")
public class MqMessageConsumer {
	

    @RabbitHandler
    public void handle(byte[] b) {
      try{
    	  System.out.println(new String(b));
      }catch(Exception e){
    	  e.printStackTrace();
      }

    }
	
}	
