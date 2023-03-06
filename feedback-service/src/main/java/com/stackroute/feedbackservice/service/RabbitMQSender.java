package com.stackroute.feedbackservice.service;

import com.stackroute.feedbackservice.model.Feedback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    public RabbitTemplate rabbitTemplate;

    /*The RabbitTemplate is injected, which accepts and forwards the messages*/
    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    String routingKey;

    /*This method sends the user data along with routing key to the queue.*/
    public void send(Feedback feedback) {
        System.out.println("Feedback sent:"+feedback.toString());
        rabbitTemplate.convertAndSend(exchange, routingKey, feedback);
    }

}
