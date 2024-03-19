package com.legaoyi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import com.legaoyi.iov.message.processor.util.ServerRuntimeContext;
import com.legaoyi.mq.MQMessageListenerManager;

@SuppressWarnings("rawtypes")
public class AppApplicationListener implements ApplicationListener {

    private static final Logger logger = LoggerFactory.getLogger(AppApplicationListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextClosedEvent) {
            stopServer();
        } else if (event instanceof ApplicationReadyEvent) {
            startServer();
        }
    }

    public void startServer() {
        logger.info("*******server started successfully");
    }

    private void stopServer() {
        // 停止接收mq消息
        try {
            ServerRuntimeContext.getBean(MQMessageListenerManager.class).stopAll();
        } catch (Exception e) {
            logger.error("", e);
        }
        logger.info("*******server stoped successfully");
    }

}
