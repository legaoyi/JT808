package com.legaoyi.iov.message.processor.tjsatl.handler;


import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "tjsatl_state" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class TjsatlDeviceStateMessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(TjsatlDeviceStateMessageHandler.class);
   
    @SuppressWarnings("unchecked")
    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<String, Object> map = (Map<String, Object>) message.getMessage();
        int state = (Integer) map.get(Constants.MAP_KEY_STATE);
        logger.info("******告警附件上传上下线消息:{}", message.toString());
    }
}
