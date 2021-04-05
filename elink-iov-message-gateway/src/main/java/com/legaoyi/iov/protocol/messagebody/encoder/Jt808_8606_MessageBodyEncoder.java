package com.legaoyi.iov.protocol.messagebody.encoder;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.downstream.messagebody.Jt808_8606_MessageBody;
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
@Component(MessageBodyEncoder.MESSAGE_BODY_ENCODER_BEAN_PREFIX + "8606" + MessageBodyEncoder.MESSAGE_BODY_ENCODER_BEAN_SUFFIX)
public class Jt808_8606_MessageBodyEncoder implements MessageBodyEncoder {

    @Override
    public byte[] encode(MessageBody message) throws IllegalMessageException {
        try {
            Jt808_8606_MessageBody messageBody = (Jt808_8606_MessageBody) message;
            MessageBuilder mb = new MessageBuilder();
            mb.addDword(messageBody.getAreaId());
            int attribute = messageBody.getAttribute();
            mb.addWord(attribute);

            if (((attribute & (1 << 0)) >> 0) == 1) {
                mb.append(ByteUtils.bcd2bytes(DateUtils.dateTime2bcd(messageBody.getStartTime()), 6));
                mb.append(ByteUtils.bcd2bytes(DateUtils.dateTime2bcd(messageBody.getEndTime()), 6));
            }

            List<Map<String, Object>> roadList = messageBody.getRoadList();

            MessageBuilder rmb = new MessageBuilder();
            int pointId = 1, count = 0;
            for (Map<String, Object> map : roadList) {
                int roadId = (Integer) map.get("roadId");
                int width = (Integer) map.get("width");
                attribute = (Integer) map.get("attribute");
                Integer ltTravelTime = (Integer) map.get("ltTravelTime");
                Integer gtTravelTime = (Integer) map.get("gtTravelTime");
                Integer limitedSpeed = (Integer) map.get("limitedSpeed");
                Integer durationTime = (Integer) map.get("durationTime");
                List<?> path = (List<?>) map.get("path");
                for (Object o : path) {
                    Map<?, ?> point = (Map<?, ?>) o;
                    rmb.addDword(pointId++);
                    rmb.addDword(roadId);
                    double lat = 1000000 * (Double.valueOf(String.valueOf(point.get("lat"))));
                    rmb.addDword(Math.abs((int) lat));

                    double lng = 1000000 * (Double.valueOf(String.valueOf(point.get("lng"))));
                    rmb.addDword(Math.abs((int) lng));
                    rmb.addByte(width);
                    if (lat < 0) {
                        attribute = attribute | (1 << 2);
                    }
                    if (lng < 0) {
                        attribute = attribute | (1 << 3);
                    }
                    rmb.addByte(attribute);
                    if (((attribute & (1 << 0)) >> 0) == 1) {
                        rmb.addWord(ltTravelTime);
                        rmb.addWord(gtTravelTime);
                    }
                    if (((attribute & (1 << 1)) >> 1) == 1) {
                        rmb.addWord(limitedSpeed);
                        rmb.addByte(durationTime);
                    }
                    count++;
                }
            }
            mb.addWord(count);
            mb.append(rmb.getBytes());
            return mb.getBytes();
        } catch (Exception e) {
            throw new IllegalMessageException(e);
        }
    }
}
