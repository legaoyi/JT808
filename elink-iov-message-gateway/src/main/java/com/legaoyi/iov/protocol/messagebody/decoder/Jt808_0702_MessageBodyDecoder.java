package com.legaoyi.iov.protocol.messagebody.decoder;

import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.exception.IllegalMessageException;
import com.legaoyi.iov.protocol.message.MessageBody;
import com.legaoyi.iov.protocol.message.decoder.MessageBodyDecoder;
import com.legaoyi.iov.protocol.upstream.messagebody.Jt808_0702_MessageBody;
import com.legaoyi.iov.protocol.util.ByteUtils;

/*
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Component(MessageBodyDecoder.MESSAGE_BODY_DECODER_BEAN_PREFIX + "0702" + MessageBodyDecoder.MESSAGE_BODY_DECODER_BEAN_SUFFIX)
public class Jt808_0702_MessageBodyDecoder implements MessageBodyDecoder {

    @Override
    public MessageBody decode(Object message) throws IllegalMessageException {
        Jt808_0702_MessageBody messageBody = new Jt808_0702_MessageBody();
        try {
            byte[] bytes = (byte[]) message;
            int offset = 0;

            int len = ByteUtils.byte2int(bytes[offset++]);

            byte[] arr = new byte[len];
            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setDriverName(ByteUtils.bytes2gbk(arr));
            offset += arr.length;

            arr = new byte[20];
            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setIdCard(ByteUtils.bytes2gbk(arr));
            offset += arr.length;

            arr = new byte[40];
            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setQualification(ByteUtils.bytes2gbk(arr));
            offset += arr.length;

            len = ByteUtils.byte2int(bytes[offset++]);

            arr = new byte[len];
            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setCertifyauth(ByteUtils.bytes2gbk(arr));

            return messageBody;
        } catch (Exception e) {
            throw new IllegalMessageException(e);
        }
    }
}
