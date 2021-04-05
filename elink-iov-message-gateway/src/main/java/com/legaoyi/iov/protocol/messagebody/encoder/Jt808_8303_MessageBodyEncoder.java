package com.legaoyi.iov.protocol.messagebody.encoder;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.downstream.messagebody.Jt808_8303_MessageBody;
import com.legaoyi.iov.protocol.exception.IllegalMessageException;
import com.legaoyi.iov.protocol.message.MessageBody;
import com.legaoyi.iov.protocol.message.encoder.MessageBodyEncoder;
import com.legaoyi.iov.protocol.util.ByteUtils;
import com.legaoyi.iov.protocol.util.MessageBuilder;

/*
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Component(MessageBodyEncoder.MESSAGE_BODY_ENCODER_BEAN_PREFIX + "8303" + MessageBodyEncoder.MESSAGE_BODY_ENCODER_BEAN_SUFFIX)
public class Jt808_8303_MessageBodyEncoder implements MessageBodyEncoder {

    @Override
    public byte[] encode(MessageBody message) throws IllegalMessageException {
        try {
            Jt808_8303_MessageBody messageBody = (Jt808_8303_MessageBody) message;
            MessageBuilder mb = new MessageBuilder();
            mb.addByte(messageBody.getType());
            if (messageBody.getType() != 0) {
                List<Map<String, Object>> infoList = messageBody.getInfoList();
                int size = infoList.size();
                mb.addByte(size);
                for (Map<String, Object> map : infoList) {
                    int infoType = (Integer) map.get("infoType");
                    String infoName = (String) map.get("infoName");
                    mb.addByte(infoType);
                    byte bytes[] = ByteUtils.gbk2bytes(infoName);
                    mb.addWord(bytes.length);
                    mb.append(bytes);
                }
            }
            return mb.getBytes();
        } catch (Exception e) {
            throw new IllegalMessageException(e);
        }
    }
}
