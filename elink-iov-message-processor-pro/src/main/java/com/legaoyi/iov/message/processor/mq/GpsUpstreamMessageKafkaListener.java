package com.legaoyi.iov.message.processor.mq;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.ServerRuntimeContext;
import com.legaoyi.mq.MQMessageHandler;


@Component("gpsUpstreamMessageKafkaListener")
@ConditionalOnProperty(name= {"elink.upstream.gps.topic","spring.kafka.bootstrap-servers"})
public class GpsUpstreamMessageKafkaListener {

    private static final Logger logger = LoggerFactory.getLogger(GpsUpstreamMessageKafkaListener.class);

    @Autowired
    @Qualifier("serverRuntimeContext")
    protected ServerRuntimeContext serverRuntimeContext;

    @KafkaListener(topics = {"${elink.upstream.gps.topic}"}, containerFactory = "batchFactory")
    public void consumerBatch(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
        ack.acknowledge();
        for (ConsumerRecord<?, ?> record : records) {
            String message = null;
            try {
                message = (String) record.value();
                if (logger.isInfoEnabled()) {
                    logger.info(message);
                }
                gpsUpstreamMessageHandler().handle(message);
            } catch (Exception e) {
                logger.error("******handle mq Message error,message={}", message, e);
            }
        }
    }

    @Bean("gpsUpstreamMessageHandler")
    public MQMessageHandler gpsUpstreamMessageHandler() {
        UpstreamMqMessageKafkaHandler handler = new UpstreamMqMessageKafkaHandler();
        List<MessageHandler> handlers = new ArrayList<MessageHandler>();
        handlers.add(ServerRuntimeContext.getBean("upstreamMessageHandler", MessageHandler.class));
        handler.setHandlers(handlers);
        return handler;
    }
}
