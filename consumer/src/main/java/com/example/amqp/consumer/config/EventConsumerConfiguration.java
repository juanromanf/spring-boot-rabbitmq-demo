package com.example.amqp.consumer.config;

import com.example.amqp.common.domain.RabbitMqKeys;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class EventConsumerConfiguration {
    
    @Bean
    public TopicExchange eventExchange() {
        
        return new TopicExchange(RabbitMqKeys.TOPIC_EXCHANGE_NAME);
    }
    
    @Bean
    public Queue queue() {
        
        return new Queue(RabbitMqKeys.ORDERS_QUEUE_NAME);
    }
    
    @Bean
    public Binding binding(Queue queue, TopicExchange eventExchange) {
        
        return BindingBuilder
                .bind(queue)
                .to(eventExchange)
                .with(RabbitMqKeys.ORDERS_ROUTING_KEY);
    }
    
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
