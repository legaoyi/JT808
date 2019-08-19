package com.legaoyi.storer.handler;

import com.legaoyi.storer.message.ExchangeMessage;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;gaoshengbo@legaoyi.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-08-18
 */
public abstract class MessageHandler {

    private MessageHandler successor;

    public MessageHandler getSuccessor() {
        return successor;
    }

    public void setSuccessor(MessageHandler successor) {
        this.successor = successor;
    }

    public abstract void handle(ExchangeMessage message) throws Exception;

}
