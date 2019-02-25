package com.yzz.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
	
	
	

	
	@Bean("firstConnectionFactory")
	public ConnectionFactory firstConnectionFactory(@Value("${spring.rabbitmq.host}") String host,
			@Value("${spring.rabbitmq.port}") Integer port,
			@Value("${spring.rabbitmq.username}") String username,
			@Value("${spring.rabbitmq.password}") String password,
			@Value("${spring.rabbitmq.virtualhost}") String virtualhost) {
		return getConnectionFactory(host, port, username, password,virtualhost);
	}
	
	
	@Bean(name = "firstFactory")
	public SimpleRabbitListenerContainerFactory firstFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
			@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		return factory;
	}
	
	@Bean(name = "firstRabbitTemplate")
	public RabbitTemplate firstRabbitTemplate(
			@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory) {
		RabbitTemplate firstRabbitTemplate = new RabbitTemplate(connectionFactory);
		return firstRabbitTemplate;
	}
	

	
	
	
	private CachingConnectionFactory getConnectionFactory(String host, Integer port, String username, String password,String virtualhost) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setPublisherConfirms(true);
		connectionFactory.setPublisherReturns(false);
		return connectionFactory;
	}
	
}
