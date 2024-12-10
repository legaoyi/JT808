package com.legaoyi.iov.message.processor.ws;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class WsConfigurator extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        // 通过配置来获取httpSession
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        if (httpSession != null) {
            config.getUserProperties().put(HttpSession.class.getName(), httpSession);
        }
    }
}
