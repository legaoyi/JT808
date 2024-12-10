package com.legaoyi.iov.message.processor.sxtjsatl.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.message.processor.handler.MessageHandler;
import com.legaoyi.iov.message.processor.tjsatl.handler.Tjsatl_2017_0704_MessageHandler;
import com.legaoyi.iov.message.processor.util.Constants;

@Component(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_sxtjsatl_2019_0704" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Sxtjsatl_2017_0704_MessageHandler extends Tjsatl_2017_0704_MessageHandler {

    @Autowired
    @Qualifier(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_sxtjsatl_2019_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX)
    private Sxtjsatl_2019_0200_MessageHandler handler;

    @Autowired
    public Sxtjsatl_2017_0704_MessageHandler(@Qualifier(Constants.ELINK_MESSAGE_PROCESSOR_BEAN_PREFIX + "jt808_tjsatl_2017_0200" + Constants.ELINK_MESSAGE_PROCESSOR_MESSAGE_HANDLER_BEAN_SUFFIX) MessageHandler handler) {
        super(handler);
    }

    
}
