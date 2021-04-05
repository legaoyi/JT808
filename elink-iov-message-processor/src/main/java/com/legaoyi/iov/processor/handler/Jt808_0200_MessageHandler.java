package com.legaoyi.iov.processor.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.processor.message.ExchangeMessage;
import com.legaoyi.iov.processor.util.Constants;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;gaoshengbo@legaoyi.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-08-18
 */
@Component(Constants.ELINK_MESSAGE_STORER_BEAN_PREFIX + "0200" + Constants.ELINK_MESSAGE_STORER_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Jt808_0200_MessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Jt808_0100_MessageHandler.class);

    @SuppressWarnings("unchecked")
    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        Map<String, Object> messageBody = (Map<String, Object>) map.get(Constants.MAP_KEY_MESSAGE_MESSAGE_BODY);
        logger.info("******位置信息，message={}", message);

        if (getSuccessor() != null) {
            getSuccessor().handle(message);
        }
    }

}
