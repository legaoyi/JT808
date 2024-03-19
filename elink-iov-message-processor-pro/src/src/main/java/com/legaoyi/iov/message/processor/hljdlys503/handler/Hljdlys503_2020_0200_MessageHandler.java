package com.legaoyi.iov.message.processor.hljdlys503.handler;

import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.tjsatl.handler.GpsMessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_2019_hljdlys503_2020_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Hljdlys503_2020_0200_MessageHandler extends GpsMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Hljdlys503_2020_0200_MessageHandler.class);

    @Autowired
    public Hljdlys503_2020_0200_MessageHandler(@Qualifier(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_2019_2019_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX) MessageHandler handler) {
        setSuccessor(handler);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        logger.info("******位置信息******,message={}", map);

        Map<String, Object> gps = (Map<String, Object>) map.get(Constants.MAP_KEY_MESSAGE_BODY);

        String gpsId = (String) gps.get("id");
        if (gpsId == null) {
            gps.put("id", gpsId);
            gpsId = UUID.randomUUID().toString().replaceAll("-", "");// todo
        }

        /** 驾驶员身份识别 **/
        Map<String, Object> driver = (Map<String, Object>) gps.get("driver");
        if (driver != null) {
            checkAttachment(message, driver, gpsId);
        }

        /** 车辆运行监测 **/
        Map<String, Object> adasAlarm = (Map<String, Object>) gps.get("adasAlarm");
        if (adasAlarm != null) {
            checkAttachment(message, adasAlarm, gpsId);
        }

        /** 驾驶员驾驶行为监测预 **/
        Map<String, Object> dsmAlarm = (Map<String, Object>) gps.get("dsmAlarm");
        if (dsmAlarm != null) {
            checkAttachment(message, dsmAlarm, gpsId);
        }

        /** 设备失效监测 **/
        Map<String, Object> deviceAlarm = (Map<String, Object>) gps.get("deviceAlarm");
        if (deviceAlarm != null) {
            checkAttachment(message, deviceAlarm, gpsId);
        }

        // 后续其他业务处理
        getSuccessor().handle(message);
    }
}
