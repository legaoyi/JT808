package com.legaoyi.iov.message.processor.handler;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;
import com.legaoyi.iov.message.processor.util.ServerRuntimeContext;

/**
 * 
 * 终端上行消息处理入口
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2015-01-30
 */
@Component("upstreamMessageHandler")
public class UpstreamMessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(UpstreamMessageHandler.class);

    private static final String DEFULT_PROTOCOL_VERSION = "tjsatl_2017";// 默认苏标

    private static final int threadPoolSize = 5;

    private static final int threadPoolQueueSize = 100;

    private ThreadPoolExecutor fixedThreadPool = null;

    @PostConstruct
    public void init() {
        fixedThreadPool = new ThreadPoolExecutor(threadPoolSize, threadPoolSize, 1000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(threadPoolQueueSize));
    }

    @Override
    public void handle(ExchangeMessage message) throws Exception {
        if (message.getMessageId().equals(ExchangeMessage.MESSAGEID_UPSTREAM_MESSAGE) || message.getMessageId().equals(ExchangeMessage.MESSAGEID_UPSTREAM_MEDIA_MESSAGE)) {
            fixedThreadPool.execute(new Runnable() {

                @SuppressWarnings("unchecked")
                public void run() {
                    try {
                        Map<?, ?> map = (Map<?, ?>) message.getMessage();
                        Map<?, ?> messageHeader = (Map<?, ?>) map.get(Constants.MAP_KEY_MESSAGE_HEADER);
                        String messageId = (String) messageHeader.get(Constants.MAP_KEY_MESSAGE_ID);
                        String deviceSn = (String) messageHeader.get(Constants.MAP_KEY_DEVICE_SN);
                        String protocol = (String) messageHeader.get(Constants.MAP_KEY_PROTOCOL);

                        Map<?, ?> device = null;// 查询设备信息，todo

                        String version = (String) messageHeader.get(Constants.MAP_KEY_PROTOCOL_VERSION);// todo
                        /**
                         * if (device != null) { message.putExtAttribute(Constants.MAP_KEY_DEVICE, device); version = (String)
                         * device.get(Constants.MAP_KEY_VERSION); } else { version = (String) messageHeader.get(Constants.MAP_KEY_VERSION); version =
                         * (version == null ? DEFULT_PROTOCOL_VERSION : version); }
                         **/

                        try {
                            String beanName = Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX.concat(protocol).concat("_").concat(version).concat("_").concat(messageId).concat(Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX);
                            if (ServerRuntimeContext.getApplicationContext().containsBean(beanName)) {
                                MessageHandler messageHandler = ServerRuntimeContext.getBean(beanName, MessageHandler.class);
                                messageHandler.handle(message);
                            }
                        } catch (Exception e) {
                            logger.error(" handler message error,message={}", message, e);
                        }
                    } catch (Exception e) {
                        logger.error(" handler message error,message={}", message, e);
                    }
                }
            });
            while (fixedThreadPool.getQueue().size() >= threadPoolQueueSize) {
                Thread.sleep(150);
            }
        } else if (getSuccessor() != null) {
            getSuccessor().handle(message);
        }
    }
}
