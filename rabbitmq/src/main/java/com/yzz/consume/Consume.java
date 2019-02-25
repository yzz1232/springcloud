package com.yzz.consume;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.GetResponse;

public class Consume {
	
	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("10.200.133.26");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("fydtest");
		connectionFactory.setPassword("fydtest");
		connectionFactory.setVirtualHost("/");
		
		
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
//		////死信队列
//		channel.exchangeDeclare("dlxexchange","direct",true,false,null);
//		channel.queueDeclare("dlxqueue", true, false, false, null);
//		channel.queueBind("dlxqueue", "dlxexchange", "#");
////		
////		//正常队列
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("x-dead-letter-exchange" , "dlxexchange");
//		channel.queueDeclare("testdlx", true, false, false, map);
//		channel.exchangeDeclare("testdlx", "direct", true);
//		channel.queueBind("testdlx", "testdlx", "#");
//		
//		
		channel.basicQos(1);
		
		
		channel.basicConsume("fd.mq.message",false, new MyConsume(channel));
		
		 //GetResponse resp = channel.basicGet("fd.mq.message", true);
		 //System.out.println(new String(resp.getBody()));
		
//		channel.close();
//		connection.close();
//	
		
		
	
	}
	
	 static class MyConsume extends DefaultConsumer{
		 
		Channel channel;
		 
		public MyConsume(Channel channel) {
			super(channel);
			this.channel = channel;
		}
		
		@Override
		public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
			System.err.println("-----------consume message----------");
			System.err.println("consumerTag: " + consumerTag);
			System.err.println("envelope: " + envelope);
			System.err.println("properties: " + properties);
			System.err.println("body: " + new String(body));
			
			
			channel.basicAck(envelope.getDeliveryTag(), false);
			
			//channel.basicNack(deliveryTag, multiple, requeue);
			
		}
		
	}
	
}
