package com.legaoyi.iov.message.processor.jt1078.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt10782016Live_2016_1801" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Jt1078_2016_1801_MessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Jt1078_2016_1801_MessageHandler.class);

    @SuppressWarnings("unchecked")
    @Override
    public void handle(ExchangeMessage message) throws Exception {
        logger.info("******视频录制文件消息：{}", message.toString());
        Map<?, ?> map = (Map<?, ?>) message.getMessage();

        Map<String, Object> messageBody = (Map<String, Object>) map.get(Constants.MAP_KEY_MESSAGE_BODY);
        int mediaType = (Integer) messageBody.get("mediaType");
        int mediaFormatCode = (Integer) messageBody.get("mediaFormatCode");
        String filePath = (String) messageBody.get("filePath");

        this.getSuccessor().handle(message);
    }
}
