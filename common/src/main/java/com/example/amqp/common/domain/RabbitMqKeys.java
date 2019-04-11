package com.example.amqp.common.domain;

public class RabbitMqKeys {
    
    public static final String TOPIC_EXCHANGE_NAME = "spring-amqp-app.eventExchange";
    
    public static final String ORDERS_QUEUE_NAME = "spring-amqp-app.orderServiceQueue";
    
    public static final String ORDERS_ROUTING_KEY = "orders.*";
}
