package com.legaoyi.iov.platform.message.processor.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.platform.message.processor.handler.MQMessageHandler;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;gaoshengbo@legaoyi.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-08-18
 */
@Component("activeMqListener")
public class ActiveMqListener {

    private final static Logger logger = LoggerFactory.getLogger(ActiveMqListener.class);

    @Autowired
    @Qualifier("upstreamMessageHandler")
    private MQMessageHandler upstreamMessageHandler;

    // 监听队列，queue类型
    @JmsListener(destination = "${spring.activemq.upstream.queue}")
    public void receiveQueue(String text) {
        try {
            upstreamMessageHandler.handle(text);
        } catch (Exception e) {
            logger.error("******handle message error,message={}", text, e);
        }
    }
}
