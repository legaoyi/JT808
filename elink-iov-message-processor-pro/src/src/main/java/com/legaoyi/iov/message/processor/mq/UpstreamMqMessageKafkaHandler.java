package com.legaoyi.iov.message.processor.mq;

import java.util.Iterator;
import java.util.List;

import com.legaoyi.common.util.JsonUtil;
import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;
import com.legaoyi.mq.MQMessageHandler;

public class UpstreamMqMessageKafkaHandler implements MQMessageHandler {

    private List<MessageHandler> handlers;

    public void setHandlers(List<MessageHandler> handlers) {
        this.handlers = handlers;
        if (handlers != null) {
            Iterator<MessageHandler> it = handlers.iterator();
            MessageHandler handler = it.next();
            while (it.hasNext()) {
                MessageHandler successor = it.next();
                handler.setSuccessor(successor);
                handler = successor;
            }
        }
    }

    public void handle(String message) throws Exception {
        if (handlers != null && !handlers.isEmpty()) {
            handlers.get(0).handle(JsonUtil.convertStringToObject(message, ExchangeMessage.class));
        }
    }

    @Override
    public void handle(byte[] bytes) throws Exception {

    }
}
