package com.legaoyi.iov.message.processor.db31t1409.handler;

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

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_db31t1409_2023_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Db31t1409_2023_0200_MessageHandler extends GpsMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Db31t1409_2023_0200_MessageHandler.class);

    @Autowired
    public Db31t1409_2023_0200_MessageHandler(@Qualifier(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_2019_tgdrta_2020_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX) MessageHandler handler) {
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

        /** 开关门视频采集信息 **/
        Map<String, Object> doorAlarm = (Map<String, Object>) gps.get("doorAlarm");
        if (doorAlarm != null) {
            // 告警附件上传
            Integer flag = (Integer) doorAlarm.get("flag");
            if (flag == null || flag != 2) {
                checkAttachment(message, doorAlarm, gpsId);
            }
        }

        // 后续其他业务处理
        getSuccessor().handle(message);
    }

}
