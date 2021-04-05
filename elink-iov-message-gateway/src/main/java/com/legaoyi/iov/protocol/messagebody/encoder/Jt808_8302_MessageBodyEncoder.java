package com.legaoyi.iov.protocol.messagebody.encoder;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.downstream.messagebody.Jt808_8302_MessageBody;
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
@Component(MessageBodyEncoder.MESSAGE_BODY_ENCODER_BEAN_PREFIX + "8302" + MessageBodyEncoder.MESSAGE_BODY_ENCODER_BEAN_SUFFIX)
public class Jt808_8302_MessageBodyEncoder implements MessageBodyEncoder {

    @Override
    public byte[] encode(MessageBody message) throws IllegalMessageException {
        try {
            Jt808_8302_MessageBody messageBody = (Jt808_8302_MessageBody) message;
            MessageBuilder mb = new MessageBuilder();
            mb.addByte(messageBody.getFlag());
            byte[] bytes = ByteUtils.gbk2bytes(messageBody.getQuestion());
            mb.addByte(bytes.length);
            mb.append(bytes);
            List<Map<String, Object>> answerList = messageBody.getAnswerList();
            int size = answerList.size();
            if (size > 0) {
                mb.addByte(size);
                for (Map<String, Object> map : answerList) {
                    int answerId = (Integer) map.get("answerId");
                    String answer = (String) map.get("answer");
                    mb.addByte(answerId);
                    bytes = ByteUtils.gbk2bytes(answer);
                    mb.addWord(bytes.length);
                    mb.append(bytes);
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
