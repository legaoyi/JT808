package com.legaoyi.iov.message.processor.db45t2634.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.tjsatl.handler.GpsMessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_db45t2634_2023_0E11" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Db45t2634_2023_0E11_MessageHandler extends GpsMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Db45t2634_2023_0E11_MessageHandler.class);

    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        logger.info("******处理0E11消息******,message={}", map);
    }

}
