package com.example.shippingservices.RabbitMq;


import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DataProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;



    public void sendMailDataToQueue(MailServiceData mailServiceData)
    {
        rabbitTemplate.convertAndSend(topicExchange.getName(),"Mail_routing",mailServiceData);
    }
}
