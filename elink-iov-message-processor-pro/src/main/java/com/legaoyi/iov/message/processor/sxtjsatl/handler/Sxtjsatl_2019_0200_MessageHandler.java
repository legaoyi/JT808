package com.legaoyi.iov.message.processor.sxtjsatl.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.tjsatl.handler.Tjsatl_2017_0200_MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_sxtjsatl_2019_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Sxtjsatl_2019_0200_MessageHandler extends Tjsatl_2017_0200_MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Sxtjsatl_2019_0200_MessageHandler.class);

    @Autowired
    public Sxtjsatl_2019_0200_MessageHandler(@Qualifier(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_1078_2011_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX) MessageHandler handler) {
        super(handler);
    }

    // 是否有报警附件
    protected int checkAttachment(ExchangeMessage message, Map<String, Object> alarm, String alarmId) {
        Integer totalFile = (Integer) alarm.get("totalFile");
        if (totalFile != null && totalFile > 0) {
            try {
                // 下发上传报警附件指令
                Map<?, ?> map = (Map<?, ?>) message.getMessage();
                Map<?, ?> messageHeader = (Map<?, ?>) map.get(Constants.MAP_KEY_MESSAGE_HEADER);

                Map<String, Object> respMessageHeader = new HashMap<String, Object>();
                String protocol = (String) messageHeader.get(Constants.MAP_KEY_PROTOCOL);
                String protocolVersion = (String) messageHeader.get(Constants.MAP_KEY_PROTOCOL_VERSION);
                respMessageHeader.put(Constants.MAP_KEY_DEVICE_SN, messageHeader.get(Constants.MAP_KEY_DEVICE_SN));
                respMessageHeader.put(Constants.MAP_KEY_PROTOCOL, protocol);
                respMessageHeader.put(Constants.MAP_KEY_PROTOCOL_VERSION, protocolVersion);
                respMessageHeader.put(Constants.MAP_KEY_MESSAGE_ID, "8208");

             
                Map<String, Object> respMessageBody = new HashMap<String, Object>();
                respMessageBody.put("ip", "127.0.0.1");
                respMessageBody.put("tcpPort", "6031");
                respMessageBody.put("terminalId", alarm.get("terminalId"));
                respMessageBody.put("alarmTime", alarm.get("alarmTime"));
                respMessageBody.put("alarmSeq", alarm.get("alarmSeq"));
                respMessageBody.put("alarmExt", alarm.get("alarmExt"));
                respMessageBody.put("totalFile", totalFile);
                respMessageBody.put("alarmId", alarmId);

                Map<String, Object> downMessage = new HashMap<String, Object>();
                downMessage.put(Constants.MAP_KEY_MESSAGE_HEADER, respMessageHeader);
                downMessage.put(Constants.MAP_KEY_MESSAGE_BODY, respMessageBody);
                // 注入下发消息处理链
                ExchangeMessage exchangeMessage = new ExchangeMessage(ExchangeMessage.MESSAGEID_DOWNSTREAM_MESSAGE, downMessage, null, message.getGatewayId());
                exchangeMessage.setExtAttribute(message.getExtAttribute());
                downstreamMessageSendHandler.handle(exchangeMessage);
                return totalFile;
            } catch (Exception e) {
                logger.info("*********send 9208 error", e);
            }
        }
        return 0;
    }

}
