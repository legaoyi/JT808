package com.legaoyi.iov.message.processor.jt905.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt905_2014_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Jt905_2014_0200_MessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Jt905_2014_0200_MessageHandler.class);

    @Override
    public void handle(ExchangeMessage message) throws Exception {
        // 消息处理链入口
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        logger.info("******位置信息******,message={}",map);
    }

}
