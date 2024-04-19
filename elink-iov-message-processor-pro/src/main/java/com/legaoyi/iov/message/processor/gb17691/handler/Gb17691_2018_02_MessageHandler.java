package com.legaoyi.iov.message.processor.gb17691.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "gb17691_2018_02" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Gb17691_2018_02_MessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Gb17691_2018_02_MessageHandler.class);

    @Override
    public void handle(ExchangeMessage message) throws Exception {
        logger.info("******GB17691,车辆数据流数据,data={}", message.toString());
    }

}
