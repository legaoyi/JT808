package com.legaoyi.iov.message.processor.db4403t363.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "db4403t363_2023_04" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Db4403t363_2023_04_MessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Db4403t363_2023_04_MessageHandler.class);

    @Override
    public void handle(ExchangeMessage message) throws Exception {
        logger.info("******db4403t363 事件数据,data={}", message.toString());
    }

}
