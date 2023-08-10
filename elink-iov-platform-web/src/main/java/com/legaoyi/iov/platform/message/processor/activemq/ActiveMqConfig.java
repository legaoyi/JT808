package com.legaoyi.iov.platform.message.processor.activemq;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.legaoyi.iov.platform.message.processor.handler.GatewayUpstreamMessageHandler;
import com.legaoyi.iov.platform.message.processor.handler.MQMessageHandler;
import com.legaoyi.iov.platform.message.processor.handler.MessageHandler;
import com.legaoyi.iov.platform.util.ServerRuntimeContext;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;gaoshengbo@legaoyi.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-08-18
 */
@Configuration
public class ActiveMqConfig {

    @Autowired
    @Qualifier("serverRuntimeContext")
    private ServerRuntimeContext serverRuntimeContext;

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

    @Bean("upstreamMessageHandler")
    public MQMessageHandler urgentUpMessageHandler() {
        GatewayUpstreamMessageHandler handler = new GatewayUpstreamMessageHandler();
        List<MessageHandler> handlers = new ArrayList<MessageHandler>();
        handlers.add(ServerRuntimeContext.getBean("deviceStateMessageHandler", MessageHandler.class));
        handlers.add(ServerRuntimeContext.getBean("deviceUpstreamMessageHandler", MessageHandler.class));
        handler.setHandlers(handlers);
        return handler;
    }

}
