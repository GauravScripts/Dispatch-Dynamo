package com.example.authentication.RabbitMq;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

    private String exchange_name="Cargo_PulseExchange1";
    private String queue_name1="User_Queue";
    private String queue_name2="Vendor_Queue";
    private String queue_name3="Mail_Queue";

    @Bean
    public TopicExchange getExchange()
    {
        return new TopicExchange(exchange_name);
    }
    @Bean
    public Queue getQueue1()
    {
        return new Queue(queue_name1);
    }
    @Bean
    public Queue getQueue2()
    {
        return new Queue(queue_name2);
    }
    @Bean
    public Queue getQueue3()
    {
        return new Queue(queue_name3);
    }
    @Bean
    public Jackson2JsonMessageConverter getProducerjsonConverter()
    {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate getRabbitTemplate(final ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getProducerjsonConverter());
        return rabbitTemplate;
    }

    @Bean
    public Binding getBinding1(Queue getQueue1,TopicExchange d)
    {
        return BindingBuilder.bind(getQueue1).to(d).with("User_routing");
    }
    @Bean
    public Binding getBinding2(Queue getQueue2,TopicExchange d)
    {
        return BindingBuilder.bind(getQueue2).to(d).with("Vendor_routing");
    }

    @Bean
    public Binding getBinding3(Queue getQueue3,TopicExchange d)
    {
        return BindingBuilder.bind(getQueue3).to(d).with("Mail_routing");
    }
}
