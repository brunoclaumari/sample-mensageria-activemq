package com.middlemessage.msgconsumer.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JmsConfig {
	
	//@Value("${spring.activemq.broker-url}")//activemq.url
	@Value("tcp://localhost:61616")
	private String brokerUrl;
	
	//@Value("${spring.activemq.user}")
	@Value("admin")
	private String user;
	
	//@Value("${spring.activemq.password}")
	@Value("admin")
	private String password;
	
	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		if("".equals(user)) {
			return new ActiveMQConnectionFactory(brokerUrl);
		}
		
		return new ActiveMQConnectionFactory(user,password, brokerUrl);
	}
	
	@Bean
	public JmsListenerContainerFactory<?> jmsFactoryTopic(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer
			) {
		
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setPubSubDomain(true);
		
		return factory;
	}
	
	@Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(connectionFactory());
    }

    @Bean
    public JmsTemplate jmsTemplateTopic() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }

}
