# Spring Boot + RabbitMq + Flux

Maven multimodule project using spring boot 2 + webflux and rabbitmq.

### producer
Exposes a rest endpoint in http://localhost:9090/orders

### consumer
RabbitMq client consumes the queue messages.

### common
Domain model.

## RabbitMq
You will need a RabbitMq server running in your local machine.

https://github.com/juanromanf/docker-rabbit-mq
