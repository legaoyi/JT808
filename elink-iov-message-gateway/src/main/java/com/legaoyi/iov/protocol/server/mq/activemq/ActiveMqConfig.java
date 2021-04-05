package com.legaoyi.iov.protocol.server.mq.activemq;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Configuration
public class ActiveMqConfig {

    @Value("${spring.activemq.upstream.queue}")
    private String upstreamQueue;

    @Value("${spring.activemq.downstream.queue}")
    private String downstreamQueue;

    @Bean("upstreamQueue")
    public Queue upstreamQueue() {
        return new ActiveMQQueue(upstreamQueue);
    }

    @Bean("downstreamQueue")
    public Queue downstreamQueue() {
        return new ActiveMQQueue(downstreamQueue);
    }
}
