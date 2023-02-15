package com.legaoyi.iov.message.processor.tjsatl.handler;

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

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "tjsatl_tjsatl_2017_1210" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Tjsatl_2017_1210_MessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Tjsatl_2017_1210_MessageHandler.class);
    
            
    @Autowired
    public Tjsatl_2017_1210_MessageHandler(@Qualifier("downstreamMessageSendHandler") MessageHandler handler) {
        setSuccessor(handler);
    }

    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        logger.info("******告警附件上传通知消息******,message={}", map);
        
        Map<?, ?> messageHeader = (Map<?, ?>) map.get(Constants.MAP_KEY_MESSAGE_HEADER);

        int result = (message.getExtAttribute(Constants.MAP_KEY_DEVICE) == null ? 1 : 0);

        //附件服务器鉴权消息，这里需要应答网关是否允许连接上传告警附件
        result = 0;//这里默认鉴权成功
        
        Map<String, Object> resp = new HashMap<String, Object>();
        resp.put("result", result);// 鉴权结果,0是通过鉴权,1鉴权失败

        // 响应终端
        Map<String, Object> respMessageHeader = new HashMap<String, Object>();
        respMessageHeader.put(Constants.MAP_KEY_DEVICE_SN, messageHeader.get(Constants.MAP_KEY_DEVICE_SN));
        respMessageHeader.put(Constants.MAP_KEY_PROTOCOL, messageHeader.get(Constants.MAP_KEY_PROTOCOL));
        respMessageHeader.put(Constants.MAP_KEY_PROTOCOL_VERSION, messageHeader.get(Constants.MAP_KEY_PROTOCOL_VERSION));
        respMessageHeader.put(Constants.MAP_KEY_MESSAGE_ID, "8001");

        Map<String, Object> respMessageBody = new HashMap<String, Object>();
        respMessageBody.put(Constants.MAP_KEY_MESSAGE_SEQ, messageHeader.get(Constants.MAP_KEY_MESSAGE_SEQ));
        respMessageBody.put(Constants.MAP_KEY_MESSAGE_ID, messageHeader.get(Constants.MAP_KEY_MESSAGE_ID));
        respMessageBody.put("result", result);

        resp.put(Constants.MAP_KEY_MESSAGE_HEADER, respMessageHeader);
        resp.put(Constants.MAP_KEY_MESSAGE_BODY, respMessageBody);

        // 注入下发终端消息处理链
        ExchangeMessage exchangeMessage = new ExchangeMessage(ExchangeMessage.MESSAGEID_PLATFORM_AUTH_RESP_MESSAGE, resp, null, message.getGatewayId());
        exchangeMessage.setExtAttribute(message.getExtAttribute());
        getSuccessor().handle(exchangeMessage);
    }
}
