package com.example.authentication.RabbitMq;


import com.example.authentication.DTO.UserData;
import com.example.authentication.DTO.VendorData;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class DataProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;


    public void sendUserDataToQueue(UserData userData)
    {
        rabbitTemplate.convertAndSend(topicExchange.getName(),"User_routing", userData);
    }

    public void sendVendorDataToQueue(VendorData vendorData)
    {
        rabbitTemplate.convertAndSend(topicExchange.getName(),"Vendor_routing",vendorData);
    }


    public void sendMailDataToQueue(MailServiceData mailServiceData)
    {
        rabbitTemplate.convertAndSend(topicExchange.getName(),"Mail_routing",mailServiceData);
    }
}
