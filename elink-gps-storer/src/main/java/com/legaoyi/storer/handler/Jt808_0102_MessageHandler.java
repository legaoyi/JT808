package com.legaoyi.storer.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.storer.message.ExchangeMessage;
import com.legaoyi.storer.util.Constants;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;gaoshengbo@legaoyi.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-08-18
 */
@Component(Constants.ELINK_MESSAGE_STORER_BEAN_PREFIX + "0102" + Constants.ELINK_MESSAGE_STORER_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Jt808_0102_MessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Jt808_0102_MessageHandler.class);


    @Autowired
    public Jt808_0102_MessageHandler(@Qualifier("downstreamMessageSendHandler") MessageHandler handler) {
        setSuccessor(handler);
    }

    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        Map<?, ?> messageHeader = (Map<?, ?>) map.get(Constants.MAP_KEY_MESSAGE_HEADER);
        String simCode = (String) messageHeader.get(Constants.MAP_KEY_SIM_CODE);
        
        
        String authCode = "e23456";
        
        Map<?, ?> messageBody = (Map<?, ?>) map.get(Constants.MAP_KEY_MESSAGE_MESSAGE_BODY);
        if (messageBody != null) {
            authCode = (String) messageBody.get("authCode");
        }
        
        //平台校验鉴权码，todo
        //这里默认鉴权成功
        int result = 0;
        
        //这里默认"2011"版本协议，平台可在录入终端是配置协议版本,todo
        String protocolVersion = "2013";

        
        //应答消息
        Map<String, Object> resp = new HashMap<String, Object>();
        resp.put(Constants.MAP_KEY_SIM_CODE, simCode);// 终端唯一标识
        resp.put("result", result);// 鉴权结果,0是通过鉴权,1鉴权失败
        resp.put(Constants.MAP_KEY_MESSAGE_SEQ, messageHeader.get(Constants.MAP_KEY_MESSAGE_SEQ));// 对应终端的消息流水号
        resp.put("authCode", authCode);// 鉴权码
        resp.put("protocolVersion", protocolVersion);// 协议版本

        // 注入下发终端消息处理链
        ExchangeMessage exchangeMessage = new ExchangeMessage(ExchangeMessage.MESSAGEID_PLATFORM_AUTH_RESP_MESSAGE, resp, null, message.getGatewayId());
        exchangeMessage.setExtAttribute(message.getExtAttribute());
        getSuccessor().handle(exchangeMessage);
    }
}
