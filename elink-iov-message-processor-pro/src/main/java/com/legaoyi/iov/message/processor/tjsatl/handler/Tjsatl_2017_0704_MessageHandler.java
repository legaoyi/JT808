package com.legaoyi.iov.message.processor.tjsatl.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_tjsatl_2017_0704" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Tjsatl_2017_0704_MessageHandler extends GpsMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Tjsatl_2017_0704_MessageHandler.class);
    
    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        logger.info("******位置信息补报******,message={}", map);
    }

}
