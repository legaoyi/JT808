package com.legaoyi.iov.processor.handler;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.processor.message.ExchangeMessage;
import com.legaoyi.iov.processor.util.Constants;
import com.legaoyi.iov.processor.util.ServerRuntimeContext;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;gaoshengbo@legaoyi.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-08-18
 */
@Component("deviceUpstreamMessageHandler")
public class DeviceUpstreamMessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(DeviceUpstreamMessageHandler.class);

    @Value("${batchSave.threadPool.size}")
    private int threadPoolSize = 5;

    private ExecutorService fixedThreadPool = null;

    @PostConstruct
    public void init() {
        fixedThreadPool = Executors.newFixedThreadPool(threadPoolSize);
    }

    @Override
    public void handle(ExchangeMessage message) throws Exception {
        logger.info("******处理数据，message={}", message);
        if (message.getMessageId().equals(ExchangeMessage.MESSAGEID_GATEWAY_UP_MESSAGE)) {
            fixedThreadPool.execute(new Runnable() {

                public void run() {
                    try {
                        Map<?, ?> map = (Map<?, ?>) message.getMessage();
                        Map<?, ?> messageHeader = (Map<?, ?>) map.get(Constants.MAP_KEY_MESSAGE_HEADER);
                        String messageId = (String) messageHeader.get(Constants.MAP_KEY_MESSAGE_ID);
                        String simCode = (String) messageHeader.get(Constants.MAP_KEY_SIM_CODE);
                        try {
                            MessageHandler messageHandler = ServerRuntimeContext.getBean(Constants.ELINK_MESSAGE_STORER_BEAN_PREFIX.concat(messageId).concat(Constants.ELINK_MESSAGE_STORER_MESSAGE_HANDLER_BEAN_SUFFIX), MessageHandler.class);
                            messageHandler.handle(message);
                        } catch (NoSuchBeanDefinitionException e) {

                        } catch (Exception e) {
                            logger.error(" handler message error,message={}", message, e);
                        }
                    } catch (Exception e) {
                        logger.error(" handler message error,message={}", message, e);
                    }
                }
            });
        } else if (getSuccessor() != null) {
            getSuccessor().handle(message);
        }
    }
}
