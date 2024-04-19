package com.legaoyi.iov.message.processor.jt905.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt905_2014_0102" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Jt905_2014_0102_MessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Jt905_2014_0102_MessageHandler.class);

    @Autowired
    public Jt905_2014_0102_MessageHandler(@Qualifier("downstreamMessageSendHandler") MessageHandler handler) {
        setSuccessor(handler);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handle(ExchangeMessage message) throws Exception {
        //jt905协议没有鉴权消息，网关模拟了一个鉴权消息
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        logger.info("******鉴权信息******,message={}",map);
        Map<?, ?> messageHeader = (Map<?, ?>) map.get(Constants.MAP_KEY_MESSAGE_HEADER);
        String deviceSn = (String) messageHeader.get(Constants.MAP_KEY_DEVICE_SN);
        String authCode = null;
        Map<String, Object> messageBody = (Map<String, Object>) map.get(Constants.MAP_KEY_MESSAGE_BODY);
        if (messageBody != null) {
            authCode = (String) messageBody.get("authCode");
        }

        Map<?, ?> device = (Map<?, ?>) message.getExtAttribute(Constants.MAP_KEY_DEVICE);
        // 这里验证鉴权，todo

        // 默认成功
        int result = 0;
        String protocol = (String)messageHeader.get(Constants.MAP_KEY_PROTOCOL);
        String version =  (String)messageHeader.get(Constants.MAP_KEY_PROTOCOL_VERSION);

        Map<String, Object> resp = new HashMap<String, Object>();
        resp.put("result", result);// 鉴权结果,0是通过鉴权,1鉴权失败
        if (result != 0) {
            logger.warn("authentication failure,message={}, deviceInfo={} ", message, device);
            messageBody.put("desc", "鉴权失败");
            messageBody.put("result", result);
        } else {
            messageBody.put("desc", "鉴权通过");
            resp.put("authCode", authCode);// 鉴权码
        }

        // 响应终端鉴权结果
        Map<String, Object> respMessageHeader = new HashMap<String, Object>();
        respMessageHeader.put(Constants.MAP_KEY_DEVICE_SN, deviceSn);
        respMessageHeader.put(Constants.MAP_KEY_PROTOCOL, protocol);
        respMessageHeader.put(Constants.MAP_KEY_PROTOCOL_VERSION, version);
        //respMessageHeader.put(Constants.MAP_KEY_MESSAGE_ID, "8001");//不需要应答终端，不传messageId

        //不需要应答终端，不传messageBody
        //Map<String, Object> respMessageBody = new HashMap<String, Object>();
        //respMessageBody.put(Constants.MAP_KEY_MESSAGE_SEQ, messageHeader.get(Constants.MAP_KEY_MESSAGE_SEQ));
        //respMessageBody.put(Constants.MAP_KEY_MESSAGE_ID, messageHeader.get(Constants.MAP_KEY_MESSAGE_ID));
        //respMessageBody.put("result", result);

        resp.put(Constants.MAP_KEY_MESSAGE_HEADER, respMessageHeader);
        //resp.put(Constants.MAP_KEY_MESSAGE_BODY, respMessageBody);

        // 注入下发终端消息处理链
        ExchangeMessage exchangeMessage = new ExchangeMessage(ExchangeMessage.MESSAGEID_PLATFORM_AUTH_RESP_MESSAGE, resp, null, message.getGatewayId());
        exchangeMessage.setExtAttribute(message.getExtAttribute());
        getSuccessor().handle(exchangeMessage);
    }
}
