package com.legaoyi.iov.message.processor.db32t3610.handler;

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

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_db32t3610_2019_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Db32t3610_2019_0200_MessageHandler extends GpsMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Db32t3610_2019_0200_MessageHandler.class);

    @Autowired
    public Db32t3610_2019_0200_MessageHandler(@Qualifier(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_tjsatl_2017_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX) MessageHandler handler) {
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
        
        /** 激烈驾驶报警信息 **/
        Map<String, Object> idmAlarm = (Map<String, Object>) gps.get("idmAlarm");
        if (idmAlarm != null) {
            // 告警附件上传
            Integer flag = (Integer) idmAlarm.get("flag");
            if (flag == null || flag != 2) {
                checkAttachment(message, idmAlarm, gpsId);
            }
        }

        // 后续其他业务处理
        getSuccessor().handle(message);
    }

}
