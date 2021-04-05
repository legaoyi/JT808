package com.legaoyi.iov.protocol.messagebody.decoder;

import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.exception.IllegalMessageException;
import com.legaoyi.iov.protocol.message.MessageBody;
import com.legaoyi.iov.protocol.message.decoder.MessageBodyDecoder;
import com.legaoyi.iov.protocol.upstream.messagebody.Jt808_0100_MessageBody;
import com.legaoyi.iov.protocol.util.ByteUtils;

/*
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Component(MessageBodyDecoder.MESSAGE_BODY_DECODER_BEAN_PREFIX + "0100" + MessageBodyDecoder.MESSAGE_BODY_DECODER_BEAN_SUFFIX)
public class Jt808_0100_MessageBodyDecoder implements MessageBodyDecoder {

    @Override
    public MessageBody decode(Object message) throws IllegalMessageException {
        Jt808_0100_MessageBody messageBody = new Jt808_0100_MessageBody();

        try {
            byte[] bytes = (byte[]) message;
            int offset = 0;

            byte[] arr = new byte[2];
            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setProvinceId(ByteUtils.word2int(arr));
            offset += arr.length;

            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setCityId(ByteUtils.word2int(arr));
            offset += arr.length;

            arr = new byte[5];
            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setMfrsId(ByteUtils.bytes2gbk(arr));
            offset += arr.length;

            arr = new byte[20];
            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setTerminalType(ByteUtils.bytes2gbk(arr));
            offset += arr.length;

            arr = new byte[7];
            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setTerminalId(ByteUtils.bytes2gbk(arr));
            offset += arr.length;

            messageBody.setVinColor(ByteUtils.byte2int(bytes[offset++]));

            arr = new byte[bytes.length - offset];
            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setVin(ByteUtils.bytes2gbk(arr));

            return messageBody;
        } catch (Exception e) {
            throw new IllegalMessageException(e);
        }
    }
}
