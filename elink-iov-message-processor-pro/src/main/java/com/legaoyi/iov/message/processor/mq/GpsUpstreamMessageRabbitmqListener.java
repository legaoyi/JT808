package com.legaoyi.iov.message.processor.mq;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.mq.UpstreamMqMessageHandler;
import com.legaoyi.iov.message.processor.util.ServerRuntimeContext;
import com.legaoyi.mq.MQMessageHandler;

@Component("gpsUpstreamMessageRabbitmqListener")
@RabbitListener(queues = "${elink.upstream.gps.topic}")
@ConditionalOnProperty(name= {"elink.upstream.gps.topic","spring.rabbitmq.host"})
public class GpsUpstreamMessageRabbitmqListener {

    private static final Logger logger = LoggerFactory.getLogger(GpsUpstreamMessageRabbitmqListener.class);

    private static final String DEFAULT_CHARSET = "UTF-8";

    @Value("${rabbitmq.message.durable}")
    private boolean durable = true;

    @Autowired
    @Qualifier("serverRuntimeContext")
    protected ServerRuntimeContext serverRuntimeContext;

    @Value("${elink.upstream.gps.topic}")
    private String gpsUpstreamMessageQueue;

    @RabbitHandler
    public void onMessage(byte[] bytes) {
        String json = null;
        try {
            json = new String(bytes, DEFAULT_CHARSET);
            if (logger.isInfoEnabled()) {
                logger.info(json);
            }
            gpsUpstreamMessageHandler().handle(json);
        } catch (Exception e) {
            logger.error("handle mq Message error,message={}", json, e);
        }
    }

    @RabbitHandler
    public void onMessage(String json) {
        try {
            if (logger.isInfoEnabled()) {
                logger.info(json);
            }
            gpsUpstreamMessageHandler().handle(json);
        } catch (Exception e) {
            logger.error("handle mq Message error,message={}", json, e);
        }
    }

    @Bean("gpsUpstreamMessageQueue")
    public Queue gpsUpstreamMessageQueue() {
        return new Queue(gpsUpstreamMessageQueue, durable);
    }

    @Bean("gpsUpstreamMessageHandler")
    public MQMessageHandler gpsUpstreamMessageHandler() {
        UpstreamMqMessageHandler handler = new UpstreamMqMessageHandler();
        List<MessageHandler> handlers = new ArrayList<MessageHandler>();
        handlers.add(ServerRuntimeContext.getBean("upstreamMessageHandler", MessageHandler.class));
        handler.setHandlers(handlers);
        return handler;
    }

}
