package com.legaoyi.iov.message.processor.jt808.handler;

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

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_2019_2019_0100" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Jt808_2019_0100_MessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Jt808_2019_0100_MessageHandler.class);

    @Autowired
    public Jt808_2019_0100_MessageHandler(@Qualifier("downstreamMessageSendHandler") MessageHandler handler) {
        setSuccessor(handler);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        logger.info("******注册信息******,message={}",map);
        
        Map<?, ?> messageHeader = (Map<?, ?>) map.get(Constants.MAP_KEY_MESSAGE_HEADER);
        Map<String, Object> messageBody = (Map<String, Object>) map.get(Constants.MAP_KEY_MESSAGE_BODY);

        String deviceSn = (String) messageHeader.get(Constants.MAP_KEY_DEVICE_SN);
        // String terminalId = (String) messageBody.get("terminalId");

        Map<String, Object> respMessageBody = new HashMap<String, Object>();
        respMessageBody.put(Constants.MAP_KEY_MESSAGE_SEQ, messageHeader.get(Constants.MAP_KEY_MESSAGE_SEQ));

        // 由于各个设备厂商对终端id理解不一样，暂时只校验sim卡号
        Map<?, ?> device = (Map<?, ?>) message.getExtAttribute(Constants.MAP_KEY_DEVICE);

        String protocol = (String)messageHeader.get(Constants.MAP_KEY_PROTOCOL);//"jt808_2019";// 默认808协议，todo
        String version = "tgdrta_2020";// 可选值：2019,1078_2019,tgdrta_2020；默认月标，根据实际情况选择，todo
        
        int result = 0;// 默认注册成功
        if (device != null) {
            // 这里校验设备是否合法，todo
        }
        respMessageBody.put("authCode", "legaoyi.com");//业务平台生成，todo
        
        if (result != 0) {
            logger.info("register failure, message={}, deviceInfo={}", message, device);
            messageBody.put("desc", "注册失败");
            messageBody.put("result", result);
        } else {
            messageBody.put("desc", "注册成功");
        }
        respMessageBody.put("result", result);

        // 应该终端注册结果
        Map<String, Object> respMessageHeader = new HashMap<String, Object>();
        respMessageHeader.put(Constants.MAP_KEY_DEVICE_SN, deviceSn);
        if (device != null) {
            //protocol = "jt808_2019";// 默认808协议，todo
            //version = "tgdrta_2020";// 可选值：2011,2013,1078_2011,1078_2013,tjsatl_2017；默认苏标，根据实际情况选择，todo
        } 
        
        respMessageHeader.put(Constants.MAP_KEY_PROTOCOL, protocol);
        respMessageHeader.put(Constants.MAP_KEY_PROTOCOL_VERSION, version);
        respMessageHeader.put(Constants.MAP_KEY_MESSAGE_ID, "8100");
        Map<String, Object> downMessage = new HashMap<String, Object>();
        downMessage.put(Constants.MAP_KEY_MESSAGE_HEADER, respMessageHeader);
        downMessage.put(Constants.MAP_KEY_MESSAGE_BODY, respMessageBody);
        // 注入下发消息处理链
        ExchangeMessage exchangeMessage = new ExchangeMessage(ExchangeMessage.MESSAGEID_DOWNSTREAM_MESSAGE, downMessage, null, message.getGatewayId());
        exchangeMessage.setExtAttribute(message.getExtAttribute());
        getSuccessor().handle(exchangeMessage);
    }
}
