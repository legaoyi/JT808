package com.legaoyi.iov.message.processor.tjsatl.handler;

import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_tjsatl_2017_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Tjsatl_2017_0200_MessageHandler extends GpsMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Tjsatl_2017_0200_MessageHandler.class);

    @Autowired
    public Tjsatl_2017_0200_MessageHandler(@Qualifier(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_1078_2011_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX) MessageHandler handler) {
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

        /** 高级驾驶辅助系统报警信息 **/
        Map<String, Object> adasAlarm = (Map<String, Object>) gps.get("adasAlarm");
        if (adasAlarm != null) {
            // 告警附件上传
            Integer flag = (Integer) adasAlarm.get("flag");
            if (flag == null || flag != 2) {
                checkAttachment(message, adasAlarm, gpsId);
            }
        }

        /** 驾驶员状态监测系统报警信息 **/
        Map<String, Object> dsmAlarm = (Map<String, Object>) gps.get("dsmAlarm");
        if (dsmAlarm != null) {
            // 告警附件上传
            Integer flag = (Integer) dsmAlarm.get("flag");
            if (flag == null || flag != 2) {
                checkAttachment(message, dsmAlarm, gpsId);
            }
        }

        /** 胎压监测系统报警信息 **/
        Map<String, Object> tpmAlarm = (Map<String, Object>) gps.get("tpmAlarm");
        if (tpmAlarm != null) {
            // 告警附件上传
            Integer flag = (Integer) tpmAlarm.get("flag");
            if (flag == null || flag != 2) {
                checkAttachment(message, tpmAlarm, gpsId);
            }
        }

        /** 盲区监测系统报警信息 **/
        Map<String, Object> bsdAlarm = (Map<String, Object>) gps.get("bsdAlarm");
        if (bsdAlarm != null) {
            // 告警附件上传
            Integer flag = (Integer) bsdAlarm.get("flag");
            if (flag == null || flag != 2) {
                checkAttachment(message, bsdAlarm, gpsId);
            }
        }

        // 后续其他业务处理
        getSuccessor().handle(message);
    }
}
