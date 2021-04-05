package com.legaoyi.iov.protocol.messagebody.encoder;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.downstream.messagebody.Jt808_8600_MessageBody;
import com.legaoyi.iov.protocol.exception.IllegalMessageException;
import com.legaoyi.iov.protocol.message.MessageBody;
import com.legaoyi.iov.protocol.message.encoder.MessageBodyEncoder;
import com.legaoyi.iov.protocol.util.ByteUtils;
import com.legaoyi.iov.protocol.util.DateUtils;
import com.legaoyi.iov.protocol.util.MessageBuilder;

/*
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Component(MessageBodyEncoder.MESSAGE_BODY_ENCODER_BEAN_PREFIX + "8600" + MessageBodyEncoder.MESSAGE_BODY_ENCODER_BEAN_SUFFIX)
public class Jt808_8600_MessageBodyEncoder implements MessageBodyEncoder {

    @Override
    public byte[] encode(MessageBody message) throws IllegalMessageException {
        try {
            Jt808_8600_MessageBody messageBody = (Jt808_8600_MessageBody) message;
            MessageBuilder mb = new MessageBuilder();
            mb.addByte(messageBody.getType());
            mb.addByte(1);
            mb.addDword(messageBody.getAreaId());

            int attribute = messageBody.getAttribute();

            Map<String, Object> area = messageBody.getArea();
            int lat = (int) (1000000 * ((Double) area.get("lat")));// lat:是Latitude的简写,表示纬度;
            int lng = (int) (1000000 * ((Double) area.get("lng")));// lng:是Longtitude的简写,表示经度

            if (lat < 0) {
                attribute = attribute | (1 << 6);
            }

            if (lng < 0) {
                attribute = attribute | (1 << 7);
            }

            mb.addWord(attribute);

            mb.addDword(Math.abs(lat));
            mb.addDword(Math.abs(lng));

            mb.addDword((Integer) area.get("radius"));
            if (((attribute & (1 << 0)) >> 0) == 1) {
                mb.append(ByteUtils.bcd2bytes(DateUtils.dateTime2bcd(messageBody.getStartTime()), 6));
                mb.append(ByteUtils.bcd2bytes(DateUtils.dateTime2bcd(messageBody.getEndTime()), 6));
            }
            if (((attribute & (1 << 1)) >> 1) == 1) {
                mb.addWord(messageBody.getLimitedSpeed());
                mb.addByte(messageBody.getDurationTime());
            }

            return mb.getBytes();
        } catch (Exception e) {
            throw new IllegalMessageException(e);
        }
    }
}
