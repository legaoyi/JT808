package com.legaoyi.iov.message.processor.mq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.legaoyi.mq.MQMessageProducer;
import com.legaoyi.rabbitmq.RabbitmqDirectExchangeMessageProducer;

@Configuration("rabbitmqConfiguration")
@ConditionalOnProperty(name= "spring.rabbitmq.host")
public class RabbitmqConfiguration {

    @Value("${rabbitmq.message.durable}")
    private boolean durable = true;

    @Value("${rabbitmq.downstream.message.exchange}")
    private String downstreamMessageExchange;

    @Bean("commonDownstreamMessageProducer")
    public MQMessageProducer commonDownstreamMessageProducer() {
        return new RabbitmqDirectExchangeMessageProducer(downstreamMessageExchange);
    }

}
