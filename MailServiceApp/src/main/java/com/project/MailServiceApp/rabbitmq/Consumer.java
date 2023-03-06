package com.project.MailServiceApp.rabbitmq;


import com.project.MailServiceApp.mailSender.SendMail;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class Consumer {
    @Autowired
    private SendMail mailSender;


    @RabbitListener(queues="Mail_Queue")
    public void sendMail(EmailDTO emailDTO)
    {
        System.out.println(emailDTO);
        mailSender.send(emailDTO.getTo(),emailDTO.getSubject(),emailDTO.getMessage());
    }
}
