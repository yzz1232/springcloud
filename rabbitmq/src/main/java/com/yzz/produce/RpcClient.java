package com.yzz.produce;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RpcClient {
	
	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("10.200.133.26");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("fydtest");
		connectionFactory.setPassword("fydtest");
		connectionFactory.setVirtualHost("/");
		
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		String correlationId = UUID.randomUUID().toString();
		
		 AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().replyTo("TEST_ACK_QUEUE").correlationId(correlationId).deliveryMode(2).
	                contentEncoding("UTF-8").build();

		channel.basicPublish("", "ORIGIN_QUEUE", properties, "test rpc".getBytes());
		
		
		//监听 回调
		channel.basicConsume("TEST_ACK_QUEUE", false,new MyConsume(channel));
		
		
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
				System.err.println("body: " + new String(body));
				channel.basicAck(envelope.getDeliveryTag(), false);
				
				//channel.basicNack(deliveryTag, multiple, requeue);
				
			}
			
		}
	
}
