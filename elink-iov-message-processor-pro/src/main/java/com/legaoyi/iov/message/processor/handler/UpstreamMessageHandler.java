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

                        Map<?, ?> device = null;// 根据协议以及deviceSn查询设备信息，todo

                        String version = (String) messageHeader.get(Constants.MAP_KEY_PROTOCOL_VERSION);

                        if (device != null) {// todo,业务平台需保存设备的协议版本，在应答中返回设备正确的协议版本，后续消息网关按该协议版本解析
                            message.putExtAttribute(Constants.MAP_KEY_DEVICE, device);
                            if (device.containsKey(Constants.MAP_KEY_PROTOCOL_VERSION)) {
                                version = (String) device.get(Constants.MAP_KEY_PROTOCOL_VERSION);
                            }
                        }

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
