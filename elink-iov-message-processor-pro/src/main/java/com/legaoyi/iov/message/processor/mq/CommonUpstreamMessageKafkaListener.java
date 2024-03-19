package com.legaoyi.iov.message.processor.mq;

import java.util.ArrayList;
import java.util.List;

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


@Component("commonUpstreamMessageKafkaListener")
@ConditionalOnProperty(name= {"elink.upstream.common.topic","spring.kafka.bootstrap-servers"})
public class CommonUpstreamMessageKafkaListener {

	private static final Logger logger = LoggerFactory.getLogger(CommonUpstreamMessageKafkaListener.class);

	@Autowired
	@Qualifier("serverRuntimeContext")
	protected ServerRuntimeContext serverRuntimeContext;

	@KafkaListener(topics = "${elink.upstream.common.topic}")
	public void listen(String message, Acknowledgment ack) {
		try {
			if (logger.isInfoEnabled()) {
				logger.info(message);
			}
			commonUpstreamMessageHandler().handle(message);
		} catch (Exception e) {
			logger.error("******handle mq Message error,message={}", message, e);
		} finally {
			ack.acknowledge();
		}
	}

	@Bean("commonUpstreamMessageHandler")
	public MQMessageHandler commonUpstreamMessageHandler() {
	    UpstreamMqMessageKafkaHandler handler = new UpstreamMqMessageKafkaHandler();
		List<MessageHandler> handlers = new ArrayList<MessageHandler>();
		handlers.add(ServerRuntimeContext.getBean("upstreamMessageHandler", MessageHandler.class));
		handler.setHandlers(handlers);
		return handler;
	}

}
