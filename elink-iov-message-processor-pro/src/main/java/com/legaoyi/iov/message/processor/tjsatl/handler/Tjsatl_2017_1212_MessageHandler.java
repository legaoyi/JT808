package com.legaoyi.iov.message.processor.tjsatl.handler;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "tjsatl_tjsatl_2017_1212" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Tjsatl_2017_1212_MessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Tjsatl_2017_1212_MessageHandler.class);

    @SuppressWarnings("unchecked")
    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        logger.info("******告警附件上传完成通知消息******,message={}", map);
        
        Map<String, Object> messageBody = (Map<String, Object>) map.get(Constants.MAP_KEY_MESSAGE_BODY);

        // 文件名称命名规则为：<文件类型>_<通道号>_<报警类型>_<序号>_<报警编号>.<后缀名>
        String fileName = (String) messageBody.get("fileName");
       
    }
}
