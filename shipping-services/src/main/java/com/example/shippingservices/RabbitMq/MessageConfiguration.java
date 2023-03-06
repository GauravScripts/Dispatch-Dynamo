package com.example.shippingservices.RabbitMq;



import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

    private String exchange_name="Cargo_PulseExchange1";
    private String queue_name3="Mail_Queue";

    @Bean
    public TopicExchange getExchange()
    {
        return new TopicExchange(exchange_name);
    }

    @Bean
    public org.springframework.amqp.core.Queue getQueue3()
    {
        return new org.springframework.amqp.core.Queue(queue_name3);
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
    public Binding getBinding3(Queue getQueue3, TopicExchange d)
    {
        return BindingBuilder.bind(getQueue3).to(d).with("Mail_routing");
    }


}
