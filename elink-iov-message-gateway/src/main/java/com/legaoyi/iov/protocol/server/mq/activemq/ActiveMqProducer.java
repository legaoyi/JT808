package com.legaoyi.iov.protocol.server.mq.activemq;

import javax.jms.Destination;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.server.handler.MessageProducer;

/*
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Component("messageProducer")
public class ActiveMqProducer implements MessageProducer {

    private final static Logger logger = LoggerFactory.getLogger(ActiveMqProducer.class);

    @Autowired
    @Qualifier("upstreamQueue")
    private Queue upstreamQueue;

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    // 发送消息，destination是发送到的队列，message是待发送的消息
    public void sendMessage(Destination destination, final String message) {
        jmsTemplate.convertAndSend(destination, message);
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }

    @Override
    public void send(final String message) {
        sendMessage(upstreamQueue, message);
    }
}
