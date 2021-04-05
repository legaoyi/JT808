package com.legaoyi.iov.protocol.server.mq.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.server.mq.MqMessageHandler;

/*
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Component("activeMqListener")
public class ActiveMqListener {

    private final static Logger logger = LoggerFactory.getLogger(ActiveMqListener.class);

    @Autowired
    @Qualifier("mqDownstreamMessageHandler")
    private MqMessageHandler downstreamMessageHandler;

    // 监听队列，queue类型
    @JmsListener(destination = "${spring.activemq.downstream.queue}")
    public void receiveQueue(String text) {
        try {
            if (logger.isInfoEnabled()) {
                logger.info(text);
            }
            downstreamMessageHandler.handle(text);
        } catch (Exception e) {
            logger.error("******handle downstream message error,message={}", text, e);
        }
    }
}
