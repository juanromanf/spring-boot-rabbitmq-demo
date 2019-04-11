package com.example.amqp.producer.controller;

import com.example.amqp.common.domain.Order;
import com.example.amqp.producer.service.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class OrdersController {
    
    private OrdersService ordersService;
    
    public OrdersController(OrdersService ordersService) {
        
        this.ordersService = ordersService;
    }
    
    @PostMapping(value = "/orders")
    public Mono<ResponseEntity<Order>> registerOrder(@RequestBody Order order) {
        
        return Mono.fromCallable(() -> {
    
            Order orderCreated = ordersService.createOrder(order);
    
            return ResponseEntity.accepted().body(orderCreated);
        });
    }
}
