package com.example.amqp.consumer.processor;

import com.example.amqp.common.domain.OrderCreatedEvent;
import com.example.amqp.common.domain.RabbitMqKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = RabbitMqKeys.ORDERS_QUEUE_NAME)
public class OrderProcessor {
    
    public static final Logger LOGGER = LoggerFactory.getLogger(OrderProcessor.class);
    
    @RabbitHandler
    public void processOrder(OrderCreatedEvent event) {
        
        LOGGER.debug("order [{}] received !", event.getOrder().getId());
    }
}
