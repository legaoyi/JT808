package com.legaoyi.iov.message.processor.jt1078.handler;


import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;

/***
 * 1078协议
 * 
 * @author 高胜波
 *
 */
@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_1078_2011_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Jt1078_2017_0200_MessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Jt1078_2017_0200_MessageHandler.class);

    @Autowired
    public Jt1078_2017_0200_MessageHandler(@Qualifier(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_2011_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX) MessageHandler handler) {
        setSuccessor(handler);
    }

    @Override
    public void handle(ExchangeMessage message) throws Exception {
        Map<?, ?> map = (Map<?, ?>) message.getMessage();
        logger.info("******位置信息******,message={}",map);
        // 后续其他业务处理
        getSuccessor().handle(message);
    }

}
