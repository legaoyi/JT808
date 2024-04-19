package com.legaoyi.iov.message.processor.tscsdx0002.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.tjsatl.handler.GpsMessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_2019_tscsdx0002_2023_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Tscsdx0002_2023_0200_MessageHandler extends GpsMessageHandler {

    

    @Autowired
    public Tscsdx0002_2023_0200_MessageHandler(@Qualifier(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_tscsdx0001_2019_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX) MessageHandler handler) {
        setSuccessor(handler);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        Map<String, Object> gps = (Map<String, Object>) map.get(Constants.MAP_KEY_MESSAGE_BODY);
      
        Map<?, ?> device = (Map<?, ?>) message.getExtAttribute(Constants.MAP_KEY_DEVICE);

        List<Map<String, Object>> alarmList = new ArrayList<Map<String, Object>>();

        /** 超重报警信息 **/
        Map<String, Object> llAlarm = (Map<String, Object>) gps.get("llAlarm");
        if (llAlarm != null) {
           //todo
        }
        
        /** 限高报警信息 **/
        Map<String, Object> lhAlarm = (Map<String, Object>) gps.get("lhAlarm");
        if (lhAlarm != null) {
           //todo
        }
        // 后续其他业务处理
        getSuccessor().handle(message);
    }

 
}
