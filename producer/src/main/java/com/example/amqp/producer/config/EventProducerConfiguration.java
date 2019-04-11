package com.example.amqp.producer.config;

import com.example.amqp.common.domain.RabbitMqKeys;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventProducerConfiguration {
    
    @Bean
    public Exchange eventExchange() {
        
        return new TopicExchange(RabbitMqKeys.TOPIC_EXCHANGE_NAME);
    }
    
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        
        return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        
        return rabbitTemplate;
    }
}
