package com.legaoyi.iov.message.processor.ws;

import java.nio.ByteBuffer;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.service.MessageService;

@ServerEndpoint(value = "/ws/walkie/{authKey}", configurator = WsConfigurator.class)
@Component("walkieWsController")
public class WalkieWsController {

    private static final Logger logger = LoggerFactory.getLogger(WalkieWsController.class);

    private static MessageService messageService;

    @Autowired
    public void setVideoMessageService(MessageService messageService) {
        WalkieWsController.messageService = messageService;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("authKey") String authKey) {
        logger.warn("******open walkie,id={},authKey={}", session.getId(), authKey);
        // 认证用户合法性,todo
        // String privateKeys = ServerRuntimeContext.getProperty("security.video.privateKey");
        // String expireIn = ServerRuntimeContext.getProperty("security.video.signature.expireIn");
        // if (!StringUtils.isEmpty(privateKeys) && !SignatureUtil.checkSignature(privateKeys.split(","), !StringUtils.isEmpty(expireIn) ?
        // Integer.parseInt(expireIn) : 0, "/walkie", authKey)) {
        // try {
        // logger.warn("******check signature error,close walkie,id={},authKey={},privateKeys={},expireIn={}", session.getId(), authKey, privateKeys,
        // expireIn);
        // session.close();
        // } catch (IOException e) {
        // }
        // }
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        logger.warn("******close walkie,id={}", session.getId());
    }

    @OnMessage(maxMessageSize = 10000000)
    public void onMessage(Session session, String message) {
        logger.info(message);
    }

    @OnMessage(maxMessageSize = 10000000)
    public void onMessage(ByteBuffer in) {
        try {
            messageService.sendIntercomData(in.array());
        } catch (Exception e) {
            logger.error("******handle Message error,message={}", e);
        }
    }

    @OnError
    public void onError(Session session, Throwable e) {
        logger.error("", e);
    }
}
