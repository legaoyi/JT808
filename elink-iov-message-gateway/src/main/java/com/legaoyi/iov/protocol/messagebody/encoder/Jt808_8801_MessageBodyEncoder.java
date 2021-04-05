package com.legaoyi.iov.protocol.messagebody.encoder;

import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.downstream.messagebody.Jt808_8801_MessageBody;
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
@Component(MessageBodyEncoder.MESSAGE_BODY_ENCODER_BEAN_PREFIX + "8801" + MessageBodyEncoder.MESSAGE_BODY_ENCODER_BEAN_SUFFIX)
public class Jt808_8801_MessageBodyEncoder implements MessageBodyEncoder {

    @Override
    public byte[] encode(MessageBody message) throws IllegalMessageException {
        try {
            Jt808_8801_MessageBody messageBody = (Jt808_8801_MessageBody) message;
            MessageBuilder mb = new MessageBuilder();
            mb.addByte(messageBody.getChannelId());
            if (messageBody.getCommandType() == 0) {
                mb.append(new byte[] {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0});
                return mb.getBytes();
            } else if (messageBody.getCommandType() == 1) {
                mb.append(ByteUtils.hex2bytes("FFFF"));
            } else {
                mb.addWord(messageBody.getTotalPicture());
            }

            mb.addWord(messageBody.getExecutionTime());
            mb.addByte(messageBody.getSaveFlag());
            mb.addByte(messageBody.getResolution());
            mb.addByte(messageBody.getQuality());
            mb.addByte(messageBody.getLuminance());
            mb.addByte(messageBody.getContrast());
            mb.addByte(messageBody.getSaturation());
            mb.addByte(messageBody.getChromaticity());
            return mb.getBytes();
        } catch (Exception e) {
            throw new IllegalMessageException(e);
        }
    }
}
