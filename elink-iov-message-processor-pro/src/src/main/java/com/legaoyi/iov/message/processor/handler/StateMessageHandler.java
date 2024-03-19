package com.legaoyi.iov.message.processor.handler;

import java.util.Map;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;
import com.legaoyi.iov.message.processor.util.ServerRuntimeContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("stateMessageHandler")
public class StateMessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(StateMessageHandler.class);

    @SuppressWarnings("unchecked")
    @Override
    public void handle(ExchangeMessage message) throws Exception {
        if (message.getMessageId().equals(ExchangeMessage.MESSAGEID_GATEWAY_LINK_STATUS_MESSAGE)) {
            Map<String, Object> map = (Map<String, Object>) message.getMessage();
            String protocol = (String) map.get(Constants.MAP_KEY_PROTOCOL);
            logger.info("******设备上下线通知消息******,message={}", map);
            // 判断是否有协议自定义处理器
            String beanName = Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX.concat(protocol).concat("_state").concat(Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX);
            if (ServerRuntimeContext.getApplicationContext().containsBean(beanName)) {
                MessageHandler messageHandler = ServerRuntimeContext.getBean(beanName, MessageHandler.class);
                messageHandler.handle(message);
            } else {
                //todo
            }
        } else if (getSuccessor() != null) {
            getSuccessor().handle(message);
        }
    }
}
