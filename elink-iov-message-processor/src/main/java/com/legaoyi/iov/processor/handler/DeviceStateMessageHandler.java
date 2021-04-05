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
@Component("deviceStateMessageHandler")
public class DeviceStateMessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(DeviceStateMessageHandler.class);

    @SuppressWarnings("unchecked")
    @Override
    public void handle(ExchangeMessage message) throws Exception {
        if (message.getMessageId().equals(ExchangeMessage.MESSAGEID_GATEWAY_LINK_STATUS_MESSAGE)) {
            Map<String, Object> map = (Map<String, Object>) message.getMessage();
            String simCode = (String) map.get(Constants.MAP_KEY_SIM_CODE);
            int state = (Integer) map.get("state");// 1：上线；0：下线
            logger.info("*******车辆上下线,message={}", message);
        } else if (getSuccessor() != null) {
            getSuccessor().handle(message);
        }
    }
}
