package com.legaoyi.iov.message.processor.handler;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component("gatewayRespDownstreamMessageHandler")
public class GatewayRespDownstreamMessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(GatewayRespDownstreamMessageHandler.class);

    @Override
    public void handle(ExchangeMessage message) throws Exception {
        if (message.getMessageId().equals(ExchangeMessage.MESSAGEID_GATEWAY_RESP_MESSAGE)) {
            Map<?, ?> map = (Map<?, ?>) message.getMessage();
            logger.info("******网关应答平台下行消息******,message={}", map);
        } else if (getSuccessor() != null) {
            getSuccessor().handle(message);
        }
    }
}
