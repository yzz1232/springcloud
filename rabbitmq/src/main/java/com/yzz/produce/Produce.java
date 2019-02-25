package com.yzz.produce;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ReturnListener;
import com.rabbitmq.client.AMQP.BasicProperties;

public class Produce {
	
	public static void main(String[] args) throws IOException, TimeoutException {
		
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("118.25.35.185");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("zzy");
		connectionFactory.setPassword("zzy1994");
		connectionFactory.setVirtualHost("/");
		
		
		Connection connection = connectionFactory.newConnection();
		
		Channel channel = connection.createChannel();
		
		channel.queueDeclare("test", true, false, false, null);
		
		
		channel.basicPublish("", "test", true, null, "test".getBytes());
		
		
		
		
		
//		String msg = "hello world";
//		String routingKey = "#1213";
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("x-message-ttl", 6000);
//		
//		AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties().builder();
//		builder.expiration("6000");
//		
////		channel.confirmSelect();
////		
////		
////		
////		channel.addConfirmListener(new ConfirmListener() {
////			
////			@Override
////			public void handleNack(long deliveryTag, boolean multiple) throws IOException {
////				System.out.println("nack......");
////				System.out.println("deliveryTag:"+deliveryTag);
////				System.out.println("multiple:"+multiple);
////			}
////			
////			@Override
////			public void handleAck(long deliveryTag, boolean multiple) throws IOException {
////				// TODO Auto-generated method stub
////				System.out.println("ack......");
////				System.out.println("deliveryTag:"+deliveryTag);
////				System.out.println("multiple:"+multiple);
////			}
////		});
//			
//		channel.addReturnListener(new ReturnListener() {
//			
//			@Override
//			public void handleReturn(int replyCode,
//		            String replyText,
//		            String exchange,
//		            String routingKey,
//		            AMQP.BasicProperties properties,
//		            byte[] body)
//					throws IOException {
//				
//				String message = new String(body);
//				System.out.println("msg:"+message);
//				System.out.println("replyCode:"+replyCode);
//				System.out.println("replyText:"+replyText);
//				System.out.println("exchange:"+exchange);
//				System.out.println("routingKey:"+routingKey);
//				
//			}
//		});
//				
//		channel.basicPublish("121", routingKey, true, null, msg.getBytes());
//		
//		
//		
	
		
		//channel.close();
		//connection.close();
	}
	
}
