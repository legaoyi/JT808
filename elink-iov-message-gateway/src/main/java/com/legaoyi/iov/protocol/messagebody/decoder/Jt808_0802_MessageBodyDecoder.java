package com.legaoyi.iov.protocol.messagebody.decoder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.exception.IllegalMessageException;
import com.legaoyi.iov.protocol.message.MessageBody;
import com.legaoyi.iov.protocol.message.decoder.MessageBodyDecoder;
import com.legaoyi.iov.protocol.upstream.messagebody.Jt808_0802_MessageBody;
import com.legaoyi.iov.protocol.upstream.messagebody.Jt808_0802_MessageBody.Multimedia;
import com.legaoyi.iov.protocol.util.ByteUtils;

/*
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Component(MessageBodyDecoder.MESSAGE_BODY_DECODER_BEAN_PREFIX + "0802" + MessageBodyDecoder.MESSAGE_BODY_DECODER_BEAN_SUFFIX)
public class Jt808_0802_MessageBodyDecoder implements MessageBodyDecoder {

    @Override
    public MessageBody decode(Object message) throws IllegalMessageException {
        Jt808_0802_MessageBody messageBody = new Jt808_0802_MessageBody();
        try {
            byte[] bytes = (byte[]) message;

            int offset = 0;

            byte[] arr = new byte[2];
            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setMessageSeq(ByteUtils.word2int(arr));
            offset += arr.length;

            System.arraycopy(bytes, offset, arr, 0, arr.length);
            int itemCount = ByteUtils.word2int(arr);
            offset += arr.length;

            List<Jt808_0802_MessageBody.Multimedia> itemList = new ArrayList<Jt808_0802_MessageBody.Multimedia>();
            MessageBodyDecoder gpsInfoDecoder = new Jt808_0200_MessageBodyDecoder();
            for (int i = 0; i < itemCount; i++) {
                arr = new byte[4];
                System.arraycopy(bytes, offset, arr, 0, arr.length);
                Long mediaDataId = ByteUtils.dword2long(arr);
                offset += arr.length;

                int mediaType = ByteUtils.byte2int(bytes[offset++]);
                int channelId = ByteUtils.byte2int(bytes[offset++]);
                int mediaFormatCode = ByteUtils.byte2int(bytes[offset++]);

                arr = new byte[28];
                System.arraycopy(bytes, offset, arr, 0, arr.length);
                offset += arr.length;

                MessageBody gpsInfo = (MessageBody) gpsInfoDecoder.decode(arr);

                Multimedia multimedia = messageBody.new Multimedia();
                multimedia.setMediaDataId(mediaDataId);
                multimedia.setMediaType(mediaType);
                multimedia.setChannelId(channelId);
                multimedia.setMediaFormatCode(mediaFormatCode);
                multimedia.setGpsInfo(gpsInfo);
                itemList.add(multimedia);
            }
            messageBody.setItemList(itemList);
            return messageBody;
        } catch (Exception e) {
            throw new IllegalMessageException(e);
        }
    }
}
