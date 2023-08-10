package com.legaoyi.iov.platform.message.processor.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.platform.message.processor.message.ExchangeMessage;
import com.legaoyi.iov.platform.service.GpsService;
import com.legaoyi.iov.platform.util.Constants;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;gaoshengbo@legaoyi.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-08-18
 */
@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Jt808_0200_MessageHandler extends MessageHandler {

    @Autowired
    @Qualifier("gpsService")
    private GpsService gpsService;

    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        Map<?, ?> messageHeader = (Map<?, ?>) map.get(Constants.MAP_KEY_MESSAGE_HEADER);
        Map<?, ?> messageBody = (Map<?, ?>) map.get(Constants.MAP_KEY_MESSAGE_MESSAGE_BODY);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put(Constants.MAP_KEY_SIM_CODE, messageHeader.get(Constants.MAP_KEY_SIM_CODE));
        data.put(Constants.MAP_KEY_MESSAGE_MESSAGE_BODY, messageBody);
        gpsService.save(data);
        
        //缓存，todo
        if (getSuccessor() != null) {
            getSuccessor().handle(message);
        }
    }

}
