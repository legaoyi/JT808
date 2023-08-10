package com.legaoyi.iov.platform.message.processor.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.platform.message.processor.message.ExchangeMessage;
import com.legaoyi.iov.platform.service.DeviceService;
import com.legaoyi.iov.platform.util.Constants;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;gaoshengbo@legaoyi.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-08-18
 */
@Component("deviceStateMessageHandler")
public class DeviceStateMessageHandler extends MessageHandler {

    @Autowired
    @Qualifier("deviceService")
    private DeviceService deviceService;

    @SuppressWarnings("unchecked")
    @Override
    public void handle(ExchangeMessage message) throws Exception {
        if (message.getMessageId().equals(ExchangeMessage.MESSAGEID_GATEWAY_LINK_STATUS_MESSAGE)) {
            Map<String, Object> map = (Map<String, Object>) message.getMessage();
            String simCode = (String) map.get(Constants.MAP_KEY_SIM_CODE);
            int state = (Integer) map.get("state");// 1：上线；0：下线

            if (state == 0) {
                deviceService.setOffline(simCode);
            } else {
                deviceService.setOnline(simCode);
            }
        } else if (getSuccessor() != null) {
            getSuccessor().handle(message);
        }
    }
}
