package com.legaoyi.iov.message.processor.tscsdx0002.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.tjsatl.handler.GpsMessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_2019_tscsdx0002_2023_0704" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Tscsdx0002_2023_0704_MessageHandler extends GpsMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Tscsdx0002_2023_0704_MessageHandler.class);

    @Autowired
    public Tscsdx0002_2023_0704_MessageHandler(@Qualifier(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_tscsdx0001_2019_0704" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX) MessageHandler handler) {
        setSuccessor(handler);
    }

    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        logger.info("******位置信息补报******,message={}", map);

        this.getSuccessor().handle(message);
    }

}
