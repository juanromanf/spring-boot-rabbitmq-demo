package com.example.amqp.producer.service;

import com.example.amqp.common.domain.Order;
import com.example.amqp.common.domain.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrdersService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersService.class);
    
    public static final String ORDER_CREATED_ROUTING_KEY = "orders.created";
    
    private RabbitTemplate rabbitTemplate;
    
    private Exchange eventExchange;
    
    
    public OrdersService(RabbitTemplate rabbitTemplate, Exchange eventExchange) {
        
        this.rabbitTemplate = rabbitTemplate;
        this.eventExchange = eventExchange;
    }
    
    public Order createOrder(Order order) {
        
        order.setId(UUID.randomUUID().toString());
        
        LOGGER.debug("sending order [{}] to queue... ", order.getId());
        
        OrderCreatedEvent event = OrderCreatedEvent.builder()
                .order(order).build();
        
        rabbitTemplate.convertAndSend(eventExchange.getName(), ORDER_CREATED_ROUTING_KEY, event);
        
        return order;
    }
}
