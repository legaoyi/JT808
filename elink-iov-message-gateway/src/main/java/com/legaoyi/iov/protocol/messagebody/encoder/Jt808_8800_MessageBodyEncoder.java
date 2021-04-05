package com.legaoyi.iov.protocol.messagebody.encoder;

import java.util.List;

import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.downstream.messagebody.Jt808_8800_MessageBody;
import com.legaoyi.iov.protocol.exception.IllegalMessageException;
import com.legaoyi.iov.protocol.message.MessageBody;
import com.legaoyi.iov.protocol.message.encoder.MessageBodyEncoder;
import com.legaoyi.iov.protocol.util.MessageBuilder;

/*
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Component(MessageBodyEncoder.MESSAGE_BODY_ENCODER_BEAN_PREFIX + "8800" + MessageBodyEncoder.MESSAGE_BODY_ENCODER_BEAN_SUFFIX)
public class Jt808_8800_MessageBodyEncoder implements MessageBodyEncoder {

    @Override
    public byte[] encode(MessageBody message) throws IllegalMessageException {
        try {
            Jt808_8800_MessageBody messageBody = (Jt808_8800_MessageBody) message;
            MessageBuilder mb = new MessageBuilder();
            mb.addDword(messageBody.getMediaDataId());
            List<Integer> resendPackgeList = messageBody.getResendPackgeList();
            if (resendPackgeList != null && !resendPackgeList.isEmpty()) {
                mb.addByte(resendPackgeList.size());
                for (int packageId : resendPackgeList) {
                    mb.addWord(packageId);
                }
            } else {
                mb.addByte(0);
            }
            return mb.getBytes();
        } catch (Exception e) {
            throw new IllegalMessageException(e);
        }
    }
}
