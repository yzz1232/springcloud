package com.yzz.produce;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ReturnListener;
import com.rabbitmq.client.AMQP.BasicProperties;

public class ReturnProducer {
	
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("10.200.133.26");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("fydtest");
		connectionFactory.setPassword("fydtest");
		connectionFactory.setVirtualHost("/");
		
		
		Connection connection = connectionFactory.newConnection();
		
		Channel channel = connection.createChannel();
		
		channel.addReturnListener(new ReturnListener() {
			
			@Override
			public void handleReturn(int replyCode,
		            String replyText,
		            String exchange,
		            String routingKey,
		            AMQP.BasicProperties properties,
		            byte[] body)
					throws IOException {
				System.out.println("body:"+new String(body));
				
			}
		});
		
		channel.basicPublish("account", "1", true, null, "12".getBytes());
	}
	
}
