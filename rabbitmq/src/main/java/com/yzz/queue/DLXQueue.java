package com.yzz.queue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class DLXQueue {
	
	public static void main(String[] args) throws IOException, TimeoutException {
		

		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("10.200.133.26");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("fydtest");
		connectionFactory.setPassword("fydtest");
		connectionFactory.setVirtualHost("/");
		
		
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		////死信队列
		channel.exchangeDeclare("dlxexchange","direct",true,false,null);
		channel.queueDeclare("dlxqueue", true, false, false, null);
		channel.queueBind("dlxqueue", "dlxexchange", "#");
		
		//正常队列
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("x-dead-letter-exchange" , " dlxexchange");
		channel.queueDeclare("DLX", true, false, false, map);
		
		channel.close();
		connection.close();
		
	}
	
}
