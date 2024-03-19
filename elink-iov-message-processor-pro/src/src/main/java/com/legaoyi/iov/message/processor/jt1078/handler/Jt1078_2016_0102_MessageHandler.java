package com.legaoyi.iov.message.processor.jt1078.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt10782016_2016_0102" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Jt1078_2016_0102_MessageHandler extends MessageHandler {

    @Autowired
    public Jt1078_2016_0102_MessageHandler(@Qualifier("downstreamMessageSendHandler") MessageHandler handler) {
        setSuccessor(handler);
    }

    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        Map<?, ?> messageHeader = (Map<?, ?>) map.get(Constants.MAP_KEY_MESSAGE_HEADER);
        String deviceSn = (String) messageHeader.get(Constants.MAP_KEY_DEVICE_SN);
        String protocol = (String) messageHeader.get(Constants.MAP_KEY_PROTOCOL);
        String version = (String) messageHeader.get(Constants.MAP_KEY_PROTOCOL_VERSION);

        // 默认成功
        int result = 0;

        // 这里验证鉴权，todo
        Map<?, ?> device = (Map<?, ?>) message.getExtAttribute(Constants.MAP_KEY_DEVICE);
        // result = (device == null ? 1 : 0);// 鉴权结果,0是通过鉴权,1鉴权失败,

        Map<String, Object> resp = new HashMap<String, Object>();
        resp.put("result", result);

        // 实时视频鉴权，只应答网关，不发送给终端，不需要传messageId
        Map<String, Object> respMessageHeader = new HashMap<String, Object>();
        respMessageHeader.put(Constants.MAP_KEY_DEVICE_SN, deviceSn);
        respMessageHeader.put(Constants.MAP_KEY_PROTOCOL, protocol);
        respMessageHeader.put(Constants.MAP_KEY_PROTOCOL_VERSION, version);
        resp.put(Constants.MAP_KEY_MESSAGE_HEADER, respMessageHeader);

        // 注入下发终端消息处理链
        ExchangeMessage exchangeMessage = new ExchangeMessage(ExchangeMessage.MESSAGEID_PLATFORM_AUTH_RESP_MESSAGE, resp, null, message.getGatewayId());
        exchangeMessage.setExtAttribute(message.getExtAttribute());
        getSuccessor().handle(exchangeMessage);
    }
}
