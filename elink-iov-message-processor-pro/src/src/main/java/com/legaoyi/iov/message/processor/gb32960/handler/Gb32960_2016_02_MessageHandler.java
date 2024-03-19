package com.legaoyi.iov.message.processor.gb32960.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "gb32960_2016_02" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Gb32960_2016_02_MessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Gb32960_2016_02_MessageHandler.class);

    @Override
    public void handle(ExchangeMessage message) throws Exception {
       
    }

}
