package com.legaoyi.iov.message.processor.gb32960.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "gb32960_2016_01" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Gb32960_2016_01_MessageHandler extends MessageHandler {

    @Autowired
    public Gb32960_2016_01_MessageHandler(@Qualifier("downstreamMessageSendHandler") MessageHandler handler) {
        setSuccessor(handler);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        Map<?, ?> messageHeader = (Map<?, ?>) map.get(Constants.MAP_KEY_MESSAGE_HEADER);
        Map<String, Object> messageBody = (Map<String, Object>) map.get(Constants.MAP_KEY_MESSAGE_BODY);
        String protocol = (String) messageHeader.get(Constants.MAP_KEY_PROTOCOL);
        String version = (String) messageHeader.get(Constants.MAP_KEY_PROTOCOL_VERSION);
        Map<?, ?> device = (Map<?, ?>) message.getExtAttribute(Constants.MAP_KEY_DEVICE);

        int result = 0;//这里默认鉴权成功
        
        /**
        if (device != null) {
            messageBody.put("desc", "鉴权通过");
            messageBody.put("result", result);

            protocol = (String) device.get(Constants.MAP_KEY_PROTOCOL);
            version = (String) device.get(Constants.MAP_KEY_VERSION);
        } else {
            result = 2;
            messageBody.put("desc", "鉴权失败");
        }*/

        Map<String, Object> resp = new HashMap<String, Object>();
        resp.put("result", result);// 鉴权结果,0是通过鉴权,1鉴权失败
        // 响应终端
        Map<String, Object> respMessageHeader = new HashMap<String, Object>();
        respMessageHeader.put(Constants.MAP_KEY_DEVICE_SN, messageHeader.get(Constants.MAP_KEY_DEVICE_SN));
        respMessageHeader.put(Constants.MAP_KEY_PROTOCOL, protocol);
        respMessageHeader.put(Constants.MAP_KEY_PROTOCOL_VERSION, version);
        respMessageHeader.put(Constants.MAP_KEY_MESSAGE_ID, "01");

        // 应答标志，flag=1
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("flag", result == 0 ? 1 : 2);
        respMessageHeader.put("attributes", attributes);

        Map<String, Object> respMessageBody = new HashMap<String, Object>();
        respMessageBody.put("time", messageBody.get("time"));

        resp.put(Constants.MAP_KEY_MESSAGE_HEADER, respMessageHeader);
        resp.put(Constants.MAP_KEY_MESSAGE_BODY, respMessageBody);

        // 注入下发终端消息处理链
        ExchangeMessage exchangeMessage = new ExchangeMessage(ExchangeMessage.MESSAGEID_PLATFORM_AUTH_RESP_MESSAGE, resp, null, message.getGatewayId());
        exchangeMessage.setExtAttribute(message.getExtAttribute());
        getSuccessor().handle(exchangeMessage);
    }

}
