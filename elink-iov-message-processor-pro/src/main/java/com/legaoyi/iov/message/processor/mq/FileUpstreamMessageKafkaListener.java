package com.legaoyi.iov.message.processor.mq;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.ServerRuntimeContext;
import com.legaoyi.mq.MQMessageHandler;

@Component("fileUpstreamMessageKafkaListener")
@ConditionalOnProperty(name= {"elink.upstream.file.topic","spring.kafka.bootstrap-servers"})
public class FileUpstreamMessageKafkaListener {

    private static final Logger logger = LoggerFactory.getLogger(FileUpstreamMessageKafkaListener.class);


    @KafkaListener(topics = "${elink.upstream.file.topic}", containerFactory = "batchFactory")
    public void consumerBatch(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
        ack.acknowledge();
        for (ConsumerRecord<?, ?> record : records) {
            String message = null;
            try {
                message = (String) record.value();
                if (logger.isInfoEnabled()) {
                    logger.info(message);
                }
                fileUpstreamMessageHandler().handle(message);
            } catch (Exception e) {
                logger.error("******handle mq Message error,message={}", message, e);
            }
        }
    }

    @Bean("fileUpstreamMessageHandler")
    public MQMessageHandler fileUpstreamMessageHandler() {
        UpstreamMqMessageKafkaHandler handler = new UpstreamMqMessageKafkaHandler();
        List<MessageHandler> handlers = new ArrayList<MessageHandler>();
        handlers.add(ServerRuntimeContext.getBean("upstreamMessageHandler", MessageHandler.class));
        handler.setHandlers(handlers);
        return handler;
    }
}
