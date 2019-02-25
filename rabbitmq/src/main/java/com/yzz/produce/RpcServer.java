package com.yzz.produce;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RpcServer {
	
	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("10.200.133.26");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("fydtest");
		connectionFactory.setPassword("fydtest");
		connectionFactory.setVirtualHost("/");
		
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.basicConsume("ORIGIN_QUEUE",false, new MyConsume(channel));
		
		
		
		
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
				
				
				String reply = properties.getReplyTo();
				String id = properties.getCorrelationId();
				
				 AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().correlationId(id).build();
			     this.getChannel().basicPublish("",reply,props,("msg").getBytes());
			        
				
				
				channel.basicAck(envelope.getDeliveryTag(), false);
				
				//channel.basicNack(deliveryTag, multiple, requeue);
				
			}
			
		}
	
}
