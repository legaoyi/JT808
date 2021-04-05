package com.legaoyi.iov.protocol.server.handler;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.downstream.messagebody.Jt808_8001_MessageBody;
import com.legaoyi.iov.protocol.message.ExchangeMessage;
import com.legaoyi.iov.protocol.message.Message;
import com.legaoyi.iov.protocol.message.MessageHeader;
import com.legaoyi.iov.protocol.messagebody.UnknownMessageBody;

@Component("responseMessageHandler")
public class ResponseMessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResponseMessageHandler.class);

    @Value("${server.resp.message}")
    private String serverRespMessage;

    private static Map<String, Object> serverRespMessageMap;

    @PostConstruct
    public void init() {
        serverRespMessageMap = new HashMap<String, Object>();
        if (!StringUtils.isBlank(serverRespMessage)) {
            String[] arr = serverRespMessage.split(",");
            for (String messageId : arr) {
                serverRespMessageMap.put(messageId, null);
            }
            serverRespMessage = null;
        }
    }

    @Override
    public void handle(ExchangeMessage exchangeMessage) throws Exception {

    }

    @Override
    public void handle(IoSession session, ExchangeMessage exchangeMessage) throws Exception {
        Message message = (Message) exchangeMessage.getMessage();
        if (message.getMessageBody() instanceof UnknownMessageBody) {
            if (StringUtils.isNotBlank(message.getMessageHeader().getSimCode())) {
                write8001Message(session, message, 1);
            }
        } else {
            if (serverRespMessageMap.containsKey(message.getMessageHeader().getMessageId())) {
                write8001Message(session, message, 0);
            }
        }
    }

    private void write8001Message(IoSession session, Message message, int result) {
        try {
            session.write(build8001Message(message.getMessageHeader(), result));
        } catch (Exception e) {
            logger.error("*******response 8001 message error,message={}", message, e);
        }
    }

    private static Message build8001Message(MessageHeader messageHeader, int result) {
        Jt808_8001_MessageBody messageBody = new Jt808_8001_MessageBody();
        messageBody.setMessageId(messageHeader.getMessageId());
        messageBody.setMessageSeq((int) messageHeader.getMessageSeq());
        messageBody.setResult(result);
        messageHeader.setMessageId(Jt808_8001_MessageBody.MESSAGE_ID);
        return new Message(0, messageHeader, messageBody);
    }
}
