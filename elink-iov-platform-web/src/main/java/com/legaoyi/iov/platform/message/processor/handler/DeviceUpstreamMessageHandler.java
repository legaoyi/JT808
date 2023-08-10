package com.legaoyi.iov.platform.message.processor.handler;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.platform.message.processor.message.ExchangeMessage;
import com.legaoyi.iov.platform.service.DeviceService;
import com.legaoyi.iov.platform.util.Constants;
import com.legaoyi.iov.platform.util.ServerRuntimeContext;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;gaoshengbo@legaoyi.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-08-18
 */
@Component("deviceUpstreamMessageHandler")
public class DeviceUpstreamMessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(DeviceUpstreamMessageHandler.class);

    @Autowired
    @Qualifier("deviceService")
    private DeviceService deviceService;

    @Value("${batchSave.threadPool.size}")
    private int threadPoolSize = 5;

    private ExecutorService fixedThreadPool = null;

    @PostConstruct
    public void init() {
        fixedThreadPool = Executors.newFixedThreadPool(threadPoolSize);
    }

    @Override
    public void handle(ExchangeMessage message) throws Exception {
        if (message.getMessageId().equals(ExchangeMessage.MESSAGEID_GATEWAY_UP_MESSAGE)) {
            fixedThreadPool.execute(new Runnable() {

                public void run() {
                    String messageId = null;
                    try {
                        Map<?, ?> map = (Map<?, ?>) message.getMessage();
                        Map<?, ?> messageHeader = (Map<?, ?>) map.get(Constants.MAP_KEY_MESSAGE_HEADER);
                        messageId = (String) messageHeader.get(Constants.MAP_KEY_MESSAGE_ID);
                        String simCode = (String) messageHeader.get(Constants.MAP_KEY_SIM_CODE);

                        Map<?, ?> device = deviceService.getBySimCode(simCode);
                        message.putExtAttribute(Constants.MAP_KEY_DEVICE, device);
                    } catch (Exception e) {
                        logger.error(" handler message error,message={}", message.toString(), e);
                    }

                    String beanName = Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX.concat(messageId).concat(Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX);
                    if (ServerRuntimeContext.getApplicationContext().containsBean(beanName)) {
                        try {
                            MessageHandler messageHandler = ServerRuntimeContext.getBean(beanName, MessageHandler.class);
                            messageHandler.handle(message);
                        } catch (Exception e) {
                            logger.error(" handler message error,message={}", message.toString(), e);
                        }
                    } else {
                        logger.info("******ignore message:{}", message.toString());
                    }
                }
            });
        } else if (getSuccessor() != null) {
            getSuccessor().handle(message);
        }
    }
}
