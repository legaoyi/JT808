package com.legaoyi.iov.message.processor.handler;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;


@Component("dataLimitAlarmMessageHandler")
public class DataLimitAlarmMessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(DataLimitAlarmMessageHandler.class);
    
    @Override
    public void handle(ExchangeMessage message) throws Exception {
        if (message.getMessageId().equals(ExchangeMessage.MESSAGEID_DATA_LIMIT_ALARM)) {
            Map<?, ?> map = (Map<?, ?>) message.getMessage();
            logger.info("******网关流量异常告警消息******,message={}", map);
        } else if (getSuccessor() != null) {
            getSuccessor().handle(message);
        }
    }
}
