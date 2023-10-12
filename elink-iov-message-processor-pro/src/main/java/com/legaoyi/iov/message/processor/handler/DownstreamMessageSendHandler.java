package com.legaoyi.iov.message.processor.handler;

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
import com.legaoyi.mq.MQMessageProducer;

/**
 * 808消息下发
 * 
 * @author gaoshengbo
 *
 */
@Component("downstreamMessageSendHandler")
public class DownstreamMessageSendHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(DownstreamMessageSendHandler.class);

    @Autowired
    @Qualifier("commonDownstreamMessageProducer")
    private MQMessageProducer commonDownstreamMessageProducer;

    @Override
    @SuppressWarnings("unchecked")
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        Map<String, Object> messageHeader = (Map<String, Object>) map.get(Constants.MAP_KEY_MESSAGE_HEADER);

        if (messageHeader != null) {
            String deviceSn = (String) messageHeader.get(Constants.MAP_KEY_DEVICE_SN);
            String protocol = (String) messageHeader.get(Constants.MAP_KEY_PROTOCOL);

            int messageSeq = 0;// 这里默认消息流水号为0，实际情况须根据设备生成消息流水号，todo
            messageHeader.put("messageSeq", messageSeq);
        }
        message.setExchangeId(UUID.randomUUID().toString());//平台生成消息记录id,网关会应答消息发送结果,todo
        logger.info("******平台下行消息(向设备发送消息)******,message={}", message.toString());
        
        commonDownstreamMessageProducer.send(message.getGatewayId(), message);
    }

}
