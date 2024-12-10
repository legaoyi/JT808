package com.legaoyi.iov.message.processor.gb32960.handler;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "gb32960_2016_0205" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Gb32960_2016_0205_MessageHandler extends MessageHandler {


    @SuppressWarnings("unchecked")
    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> device = (Map<?, ?>) message.getExtAttribute(Constants.MAP_KEY_DEVICE);
        String deviceId = (String) device.get(Constants.MAP_KEY_DEVICE_ID);
        
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        Map<?, ?> messageBody = (Map<?, ?>) map.get(Constants.MAP_KEY_MESSAGE_BODY);
        List<?> infoList = (List<?>) messageBody.get("infoList");
        for (Object o1 : infoList) {
            Map<String, Object> info = (Map<String, Object>) o1;
            String infoType = (String) info.get("infoType");
            
        }
    }
}
