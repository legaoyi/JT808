package com.legaoyi.iov.message.processor.rabbitmq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.legaoyi.mq.MQMessageProducer;
import com.legaoyi.rabbitmq.RabbitmqDirectExchangeMessageProducer;

@Configuration("rabbitmqConfiguration")
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
