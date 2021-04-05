package com.legaoyi.iov.protocol.messagebody.decoder;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.exception.IllegalMessageException;
import com.legaoyi.iov.protocol.message.MessageBody;
import com.legaoyi.iov.protocol.message.decoder.MessageBodyDecoder;
import com.legaoyi.iov.protocol.upstream.messagebody.Jt808_0200_MessageBody;
import com.legaoyi.iov.protocol.util.ByteUtils;
import com.legaoyi.iov.protocol.util.DateUtils;

/*
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Component(MessageBodyDecoder.MESSAGE_BODY_DECODER_BEAN_PREFIX + "0200" + MessageBodyDecoder.MESSAGE_BODY_DECODER_BEAN_SUFFIX)
public class Jt808_0200_MessageBodyDecoder implements MessageBodyDecoder {

    @Override
    public MessageBody decode(Object message) throws IllegalMessageException {
        Jt808_0200_MessageBody messageBody = new Jt808_0200_MessageBody();
        try {
            byte[] bytes = (byte[]) message;

            int offset = 0;

            byte[] arr = new byte[4];
            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setAlarm(ByteUtils.dword2long(arr));
            offset += arr.length;

            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setState(ByteUtils.dword2long(arr));
            offset += arr.length;

            System.arraycopy(bytes, offset, arr, 0, arr.length);
            Double lat = Double.valueOf(String.format("%.6f", ByteUtils.dword2long(arr) * 0.000001));
            if (((messageBody.getState() & (1 << 2)) >> 2) == 1) {
                lat = -lat;
            }
            messageBody.setLat(lat);
            offset += arr.length;

            System.arraycopy(bytes, offset, arr, 0, arr.length);
            Double lng = Double.valueOf(String.format("%.6f", ByteUtils.dword2long(arr) * 0.000001));
            if (((messageBody.getState() & (1 << 3)) >> 3) == 1) {
                lng = -lng;
            }
            messageBody.setLng(lng);
            offset += arr.length;

            arr = new byte[2];
            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setAltitude((int) ByteUtils.bytes2SignedLong(arr));// 有可能出现负数，扩展一下协议，最高位做为符号位
            offset += arr.length;

            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setSpeed(Double.valueOf(String.format("%.1f", ByteUtils.word2int(arr) * 0.1)));
            offset += arr.length;

            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setDirection(ByteUtils.word2int(arr));
            offset += arr.length;

            arr = new byte[6];
            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setTime(DateUtils.bcd2timestamp(ByteUtils.bytes2bcd(arr)));
            offset += arr.length;

            while (offset < bytes.length) {
                int param = ByteUtils.byte2int(bytes[offset++]);
                int length = ByteUtils.byte2int(bytes[offset++]);

                if (param == 0x01) {
                    arr = new byte[4];
                    System.arraycopy(bytes, offset, arr, 0, arr.length);
                    messageBody.setMileage(Double.valueOf(String.format("%.1f", ByteUtils.dword2long(arr) * 0.1)));
                    offset += arr.length;
                } else if (param == 0x02) {
                    arr = new byte[2];
                    System.arraycopy(bytes, offset, arr, 0, arr.length);
                    messageBody.setOilmass(Double.valueOf(String.format("%.1f", ByteUtils.word2int(arr) * 0.1)));
                    offset += arr.length;
                } else if (param == 0x03) {
                    arr = new byte[2];
                    System.arraycopy(bytes, offset, arr, 0, arr.length);
                    messageBody.setDvrSpeed(Double.valueOf(String.format("%.1f", ByteUtils.word2int(arr) * 0.1)));
                    offset += arr.length;
                } else if (param == 0x04) {
                    arr = new byte[2];
                    System.arraycopy(bytes, offset, arr, 0, arr.length);
                    messageBody.setAlarmEventId(ByteUtils.word2int(arr));
                    offset += arr.length;
                } else if (param == 0x11) {
                    Map<String, Object> overSpeedAlarmExt = new HashMap<String, Object>();
                    overSpeedAlarmExt.put("type", ByteUtils.byte2int(bytes[offset++]));
                    if (length > 1) {
                        arr = new byte[4];
                        System.arraycopy(bytes, offset, arr, 0, arr.length);
                        overSpeedAlarmExt.put("id", ByteUtils.dword2long(arr));
                        offset += arr.length;
                    }

                    messageBody.setOverSpeedAlarmExt(overSpeedAlarmExt);
                } else if (param == 0x12) {
                    Map<String, Object> inOutAlarmExt = new HashMap<String, Object>();
                    inOutAlarmExt.put("type", ByteUtils.byte2int(bytes[offset++]));

                    arr = new byte[4];
                    System.arraycopy(bytes, offset, arr, 0, arr.length);
                    inOutAlarmExt.put("id", ByteUtils.dword2long(arr));
                    offset += arr.length;

                    inOutAlarmExt.put("direction", ByteUtils.byte2int(bytes[offset++]));

                    messageBody.setInOutAlarmExt(inOutAlarmExt);
                } else if (param == 0x13) {
                    Map<String, Object> runningTimeAlarmExt = new HashMap<String, Object>();
                    arr = new byte[4];
                    System.arraycopy(bytes, offset, arr, 0, arr.length);
                    runningTimeAlarmExt.put("id", ByteUtils.dword2long(arr));
                    offset += arr.length;

                    arr = new byte[2];
                    System.arraycopy(bytes, offset, arr, 0, arr.length);
                    runningTimeAlarmExt.put("runTime", ByteUtils.word2int(arr));
                    offset += arr.length;

                    runningTimeAlarmExt.put("result", ByteUtils.byte2int(bytes[offset++]));

                    messageBody.setRunningTimeAlarmExt(runningTimeAlarmExt);
                } else if (param == 0x25) {
                    arr = new byte[4];
                    System.arraycopy(bytes, offset, arr, 0, arr.length);
                    messageBody.setExtSignal(ByteUtils.dword2long(arr));
                    offset += arr.length;
                } else if (param == 0x2A) {
                    arr = new byte[2];
                    System.arraycopy(bytes, offset, arr, 0, arr.length);
                    messageBody.setIo(ByteUtils.word2int(arr));
                    offset += arr.length;
                } else if (param == 0x2B) {
                    arr = new byte[4];
                    System.arraycopy(bytes, offset, arr, 0, arr.length);
                    messageBody.setAd(ByteUtils.dword2long(arr));
                    offset += arr.length;
                } else if (param == 0x30) {
                    messageBody.setRssi(ByteUtils.byte2int(bytes[offset++]));
                } else if (param == 0x31) {
                    messageBody.setGnss(ByteUtils.byte2int(bytes[offset++]));
                } else {
                    arr = new byte[length];
                    System.arraycopy(bytes, offset, arr, 0, arr.length);
                    messageBody.addExt(Integer.toHexString(param), ByteUtils.bytes2hex(arr));
                    offset += length;
                }
            }
            return messageBody;
        } catch (Exception e) {
            throw new IllegalMessageException(e);
        }
    }
}
