package com.legaoyi.iov.message.processor.gb32960.handler;

import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "gb32960_2016_0201" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Gb32960_2016_0201_MessageHandler extends MessageHandler {

    @Override
    public void handle(ExchangeMessage message) throws Exception {

    }
}
