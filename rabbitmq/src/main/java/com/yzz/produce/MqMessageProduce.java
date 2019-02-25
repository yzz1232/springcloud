package com.yzz.produce;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqMessageProduce {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	
	public void send(String msg){
		
		rabbitTemplate.convertAndSend(exchange, routingKey, object);
		
	}
}
