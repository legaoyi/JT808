package com.legaoyi.iov.message.processor.jt1078.handler;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt10782016Live_state" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Jt1078DeviceStateMessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Jt1078DeviceStateMessageHandler.class);

    @Autowired
    @Qualifier("downstreamMessageSendHandler")
    private MessageHandler downstreamMessageSendHandler;

    @Override
    public void handle(ExchangeMessage message) throws Exception {
        // 视频推流上下线,消息发布，todo
        logger.info("******车载设备视频推流上下线消息:{}", message.toString());

        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        String deviceSn = (String) map.get(Constants.MAP_KEY_DEVICE_SN);

        // todo
        Map<?, ?> device = (Map<?, ?>) message.getExtAttribute(Constants.MAP_KEY_DEVICE);
        // 1：上线；0：下线
        int state = (Integer) map.get(Constants.MAP_KEY_STATE);
        if (state == 1) {
            // 下发1078协议上级平台拉流鉴权消息
            String authorizeCode = null;// 809协议时效口令,todo
            if (authorizeCode != null && device != null) {
                Map<String, Object> respMessageHeader = new HashMap<String, Object>();
                respMessageHeader.put(Constants.MAP_KEY_DEVICE_SN, deviceSn);
                respMessageHeader.put(Constants.MAP_KEY_PROTOCOL, map.get(Constants.MAP_KEY_PROTOCOL));
                respMessageHeader.put(Constants.MAP_KEY_PROTOCOL_VERSION, map.get(Constants.MAP_KEY_PROTOCOL_VERSION));

                Map<String, Object> respMessageBody = new HashMap<String, Object>();
                respMessageBody.put(Constants.MAP_KEY_DEVICE_SN, deviceSn);
                respMessageBody.put("plateColor", device.get("plateColor"));
                respMessageBody.put("plateNumber", "plateNumber");
                respMessageBody.put("authorizeCode", authorizeCode);// 上级平台统一使用该时效口令,64位

                Map<String, Object> resp = new HashMap<String, Object>();
                resp.put(Constants.MAP_KEY_MESSAGE_HEADER, respMessageHeader);
                resp.put(Constants.MAP_KEY_MESSAGE_BODY, respMessageBody);

                downstreamMessageSendHandler.handle(new ExchangeMessage("8009", resp, null, message.getGatewayId()));
            }
        } else {
            //视频下线了， 检查平台是否仍然有人在观看，决定是否重发指令，todo
        }
    }
}
