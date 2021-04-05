package com.legaoyi.iov.protocol.server.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.message.ExchangeMessage;
import com.legaoyi.iov.protocol.message.Message;
import com.legaoyi.iov.protocol.messagebody.UnknownMessageBody;

@Component("upstreamMessageHandler")
public class UpstreamMessageHandler extends MessageHandler {

    @Autowired
    @Qualifier("messageProducer")
    private MessageProducer messageProducer;

    @Override
    public void handle(ExchangeMessage exchangeMessage) throws Exception {
        Message message = (Message) exchangeMessage.getMessage();
        if (message.getMessageHeader().isSubpackage()) {
            // 分包请自行处理
            UnknownMessageBody messageBody = (UnknownMessageBody) message.getMessageBody();
            messageBody.getMessageBody(); // 十六进制字符串
        }

        messageProducer.send(exchangeMessage.toString());

        // 其他消息处理链
        if (this.getSuccessor() != null) {
            this.getSuccessor().handle(exchangeMessage);
        }
    }

}
