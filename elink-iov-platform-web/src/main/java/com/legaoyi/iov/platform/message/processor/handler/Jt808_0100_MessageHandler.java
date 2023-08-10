package com.legaoyi.iov.platform.message.processor.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.platform.message.processor.message.ExchangeMessage;
import com.legaoyi.iov.platform.util.Constants;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;gaoshengbo@legaoyi.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-08-18
 */
@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "0100" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Jt808_0100_MessageHandler extends MessageHandler {

    @Autowired
    public Jt808_0100_MessageHandler(@Qualifier("downstreamMessageSendHandler") MessageHandler handler) {
        setSuccessor(handler);
    }

    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        Map<?, ?> messageHeader = (Map<?, ?>) map.get(Constants.MAP_KEY_MESSAGE_HEADER);

        String simCode = (String) messageHeader.get(Constants.MAP_KEY_SIM_CODE);

        int result = 4;
        String authCode = null;
        Map<?, ?> device = (Map<?, ?>) message.getExtAttribute(Constants.MAP_KEY_DEVICE);
        if (device != null) {
            result = 0;
            authCode = (String) device.get("authCode");
        }

        Map<String, Object> respMessageBody = new HashMap<String, Object>();
        respMessageBody.put(Constants.MAP_KEY_MESSAGE_SEQ, messageHeader.get(Constants.MAP_KEY_MESSAGE_SEQ));
        respMessageBody.put("authCode", authCode);
        respMessageBody.put("result", result);

        // 响应终端
        Map<String, Object> respMessageHeader = new HashMap<String, Object>();
        respMessageHeader.put(Constants.MAP_KEY_SIM_CODE, simCode);
        respMessageHeader.put(Constants.MAP_KEY_MESSAGE_ID, "8100");
        Map<String, Object> downMessage = new HashMap<String, Object>();
        downMessage.put(Constants.MAP_KEY_MESSAGE_HEADER, respMessageHeader);
        downMessage.put(Constants.MAP_KEY_MESSAGE_MESSAGE_BODY, respMessageBody);
        // 注入下发消息处理链
        ExchangeMessage exchangeMessage = new ExchangeMessage(ExchangeMessage.MESSAGEID_PLATFORM_DOWN_MESSAGE, downMessage, null);
        exchangeMessage.setExtAttribute(message.getExtAttribute());
        getSuccessor().handle(exchangeMessage);
    }
}
