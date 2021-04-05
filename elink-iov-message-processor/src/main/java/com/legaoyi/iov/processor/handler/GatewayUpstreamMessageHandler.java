package com.legaoyi.iov.processor.handler;

import java.util.Iterator;
import java.util.List;

import com.legaoyi.iov.processor.message.ExchangeMessage;
import com.legaoyi.iov.processor.util.JsonUtil;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;gaoshengbo@legaoyi.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-08-18
 */
public class GatewayUpstreamMessageHandler implements MQMessageHandler {

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
}
