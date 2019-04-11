package com.example.amqp.consumer.config;

import com.example.amqp.common.domain.RabbitMqKeys;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class EventConsumerConfiguration implements RabbitListenerConfigurer {
    
    @Bean
    public Exchange eventExchange() {
        
        return new TopicExchange(RabbitMqKeys.TOPIC_EXCHANGE_NAME);
    }
    
    @Bean
    public Queue queue() {
        
        return new Queue(RabbitMqKeys.ORDERS_QUEUE_NAME);
    }
    
    @Bean
    public Binding binding(Queue queue, Exchange eventExchange) {
        
        return BindingBuilder
                .bind(queue)
                .to(eventExchange)
                .with(RabbitMqKeys.ORDERS_ROUTING_KEY)
                .noargs();
    }
    
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        
        return new MappingJackson2MessageConverter();
    }
    
    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        
        return factory;
    }
    
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
}
