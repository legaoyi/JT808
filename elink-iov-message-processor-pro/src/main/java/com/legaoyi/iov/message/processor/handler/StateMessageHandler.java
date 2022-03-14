package com.legaoyi.iov.message.processor.handler;

import java.util.Map;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

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
            logger.info("******设备上下线通知消息******,message={}", map);
        } else if (getSuccessor() != null) {
            getSuccessor().handle(message);
        }
    }
}
