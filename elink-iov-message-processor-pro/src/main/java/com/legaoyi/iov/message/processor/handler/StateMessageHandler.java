package com.legaoyi.iov.message.processor.handler;

import java.util.HashMap;
import java.util.Map;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.CacheManager;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;
import com.legaoyi.iov.message.processor.util.ServerRuntimeContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("stateMessageHandler")
public class StateMessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(StateMessageHandler.class);

    @Autowired
    @Qualifier("iovCacheManager")
    private CacheManager cacheManager;

    @SuppressWarnings("unchecked")
    @Override
    public void handle(ExchangeMessage message) throws Exception {
        if (message.getMessageId().equals(ExchangeMessage.MESSAGEID_GATEWAY_LINK_STATUS_MESSAGE)) {
            Map<String, Object> map = (Map<String, Object>) message.getMessage();
            String protocol = (String) map.get(Constants.MAP_KEY_PROTOCOL);
            String protocolVersion = (String) map.get(Constants.MAP_KEY_PROTOCOL_VERSION);
            String deviceSn = (String) map.get(Constants.MAP_KEY_DEVICE_SN);
            Integer state = (Integer) map.get(Constants.MAP_KEY_STATE);

            logger.info("******设备上下线通知消息******,message={}", map);
            // 判断是否有协议自定义处理器
            String beanName = Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX.concat(protocol).concat("_state").concat(Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX);
            if (ServerRuntimeContext.getApplicationContext().containsBean(beanName)) {
                MessageHandler messageHandler = ServerRuntimeContext.getBean(beanName, MessageHandler.class);
                messageHandler.handle(message);
            } else {
                // todo

                // 集群部署时需处理设备网络异常造成不同网关上下线问题,todo
                Map<String, Object> device = new HashMap<String, Object>();
                device.put(Constants.MAP_KEY_PROTOCOL, protocol);
                device.put(Constants.MAP_KEY_PROTOCOL_VERSION, protocolVersion);
                device.put(Constants.MAP_KEY_DEVICE_SN, deviceSn);
                device.put(Constants.MAP_KEY_STATE, state);
                device.put("gatewayId", message.getGatewayId());
                device.put("time", message.getCreateTime());
                cacheManager.addDevice(deviceSn, device);
            }

        } else if (getSuccessor() != null) {
            getSuccessor().handle(message);
        }
    }
}
