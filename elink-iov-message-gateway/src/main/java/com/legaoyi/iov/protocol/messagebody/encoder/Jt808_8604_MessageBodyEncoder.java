package com.legaoyi.iov.protocol.messagebody.encoder;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.downstream.messagebody.Jt808_8604_MessageBody;
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
@Component(MessageBodyEncoder.MESSAGE_BODY_ENCODER_BEAN_PREFIX + "8604" + MessageBodyEncoder.MESSAGE_BODY_ENCODER_BEAN_SUFFIX)
public class Jt808_8604_MessageBodyEncoder implements MessageBodyEncoder {

    @Override
    public byte[] encode(MessageBody message) throws IllegalMessageException {
        try {
            Jt808_8604_MessageBody messageBody = (Jt808_8604_MessageBody) message;
            MessageBuilder mb = new MessageBuilder();
            mb.addDword(messageBody.getAreaId());
            int attribute = messageBody.getAttribute();

            boolean westLng = false, southLat = true;
            List<Map<String, Double>> vertexList = messageBody.getVertexList();
            for (Map<String, Double> map : vertexList) {
                if ((Double) map.get("lat") < 0) {
                    southLat = true;
                }

                if ((Double) map.get("lng") < 0) {
                    westLng = true;
                }
            }

            if (southLat) {
                attribute = attribute | (1 << 6);
            }

            if (westLng) {
                attribute = attribute | (1 << 7);
            }

            mb.addWord(attribute);

            if (((attribute & (1 << 0)) >> 0) == 1) {
                mb.append(ByteUtils.bcd2bytes(DateUtils.dateTime2bcd(messageBody.getStartTime()), 6));
                mb.append(ByteUtils.bcd2bytes(DateUtils.dateTime2bcd(messageBody.getEndTime()), 6));
            }

            if (((attribute & (1 << 1)) >> 1) == 1) {
                mb.addWord(messageBody.getLimitedSpeed());
                mb.addByte(messageBody.getDurationTime());
            }

            mb.addWord(vertexList.size());
            for (Map<String, Double> map : vertexList) {
                double lat = 1000000 * (map.get("lat"));
                mb.addDword(Math.abs((int) lat));
                double lng = 1000000 * map.get("lng");
                mb.addDword(Math.abs((int) lng));
            }
            return mb.getBytes();
        } catch (Exception e) {
            throw new IllegalMessageException(e);
        }
    }
}
