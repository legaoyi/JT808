package com.legaoyi.iov.message.processor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.legaoyi.iov.message.processor.service.MessageService;
import com.legaoyi.iov.message.processor.util.CacheManager;
import com.legaoyi.iov.message.processor.util.Constants;
import com.legaoyi.iov.message.processor.util.ExchangeMessage;
import com.legaoyi.iov.message.processor.util.IntercomMessageBuilder;
import com.legaoyi.iov.message.processor.util.MessageSeqService;
import com.legaoyi.mq.MQMessageProducer;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    @Qualifier("commonDownstreamMessageProducer")
    private MQMessageProducer downstreamMessageProducer;

    @Autowired
    @Qualifier("iovCacheManager")
    private CacheManager cacheManager;

    @Autowired
    @Qualifier("messageSeqService")
    private MessageSeqService messageSeqService;

    @Override
    public Map<String, Object> send(String deviceSn, String messageId, Map<String, Object> messageBody) throws Exception {
        // 判断当前设备对应通道是否已经在推流,已在推流则不能再发指令，todo

        Map<String, Object> device = cacheManager.getDevice(deviceSn);
        if (device != null) {
            Integer state = (Integer) device.get(Constants.MAP_KEY_STATE);
            if (state != null && state == 1) {
                Map<String, Object> messageHeader = new HashMap<String, Object>();
                messageHeader.put("deviceSn", deviceSn);
                messageHeader.put("protocol", device.get(Constants.MAP_KEY_PROTOCOL));
                messageHeader.put("protocolVersion", device.get(Constants.MAP_KEY_PROTOCOL_VERSION));
                messageHeader.put("messageId", messageId);
                messageHeader.put("messageSeq", messageSeqService.next(deviceSn, messageId));

                Map<String, Object> message = new HashMap<String, Object>();
                message.put("messageHeader", messageHeader);
                message.put("messageBody", messageBody);

                ExchangeMessage exchangeMessage = new ExchangeMessage(ExchangeMessage.MESSAGEID_DOWNSTREAM_MESSAGE, message, UUID.randomUUID().toString());// 平台生成消息记录id,网关会应答消息发送结果,todo

                String gatewayId = (String) device.get("gatewayId");
                downstreamMessageProducer.send(gatewayId, exchangeMessage);

                return message;
            }
        }

        throw new Exception("设备不存在或者不在线！");
    }

    @Override
    public Map<String, Object> startLive(String deviceSn, Map<String, Object> messageBody) throws Exception {
        // String ip = "127.0.0.1"; // todo,最好是后台配置
        // int port = 6037; // todo,最好是后台配置
        // messageBody.put("ip", ip);
        // messageBody.put("udpPort", 0);
        // messageBody.put("tcpPort", port);

        return this.send(deviceSn, "9101", messageBody);
    }

    @Override
    public Map<String, Object> stopLive(String deviceSn, Map<String, Object> messageBody) throws Exception {
        return this.send(deviceSn, "9102", messageBody);
    }

    @Override
    public void sendIntercomData(byte[] bytes) throws Exception {
        int offset = 0;
        // 帧头
        byte[] frameHeader = new byte[5 + 1 + 2];
        System.arraycopy(bytes, offset, frameHeader, 0, frameHeader.length);
        offset += frameHeader.length;

        // 广播数量
        int n = IntercomMessageBuilder.byte2int(bytes[offset++]);
        List<Map<String, Object>> deviceList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < n; i++) {
            // 终端设备sim卡号
            int length = IntercomMessageBuilder.byte2int(bytes[offset++]);
            byte[] simBytes = new byte[length];
            System.arraycopy(bytes, offset, simBytes, 0, simBytes.length);
            String deviceSn = IntercomMessageBuilder.bytes2bcd(simBytes);
            offset += simBytes.length;

            // 逻辑通道号
            int channelId = IntercomMessageBuilder.byte2int(bytes[offset++]);

            Map<String, Object> device = new HashMap<String, Object>();
            device.put("deviceSn", deviceSn);
            device.put("channelId", channelId);
            deviceList.add(device);
        }

        // 数据类型 + 分包处理标志 + 时间戳
        byte[] frameTime = new byte[9];
        System.arraycopy(bytes, offset, frameTime, 0, frameTime.length);
        offset += frameTime.length;

        // pcm数据长度
        byte[] frameLen = new byte[2];
        System.arraycopy(bytes, offset, frameLen, 0, frameLen.length);
        offset += frameLen.length;

        // pcm数据
        byte[] data = new byte[IntercomMessageBuilder.word2int(frameLen)];
        System.arraycopy(bytes, offset, data, 0, data.length);

        for (Map<String, Object> device : deviceList) {
            // 查找gatewayId
            String deviceSn = (String) device.get("deviceSn");
            int channelId = (Integer) device.get("channelId");

            String gatewayId = (String) cacheManager.get(deviceSn + "_" + channelId + "_1");
            if (!StringUtils.isEmpty(gatewayId)) {
                IntercomMessageBuilder mb = new IntercomMessageBuilder();
                mb.append(frameHeader);
                mb.addByte(1);
                byte[] simBytes = IntercomMessageBuilder.bcd2bytes(deviceSn);
                mb.addByte(simBytes.length);
                mb.append(simBytes);
                mb.addByte(channelId);
                mb.append(frameTime);
                mb.append(frameLen);
                mb.append(data);
                downstreamMessageProducer.send(gatewayId.concat("-intercom"), mb.getHexes());
            }
        }
    }

    @Override
    public void sendIntercomData1(byte[] bytes) throws Exception {
        int offset = 0;
        // 帧头
        byte[] frameHeader = new byte[5 + 1 + 2];
        System.arraycopy(bytes, offset, frameHeader, 0, frameHeader.length);
        offset += frameHeader.length;

        // 终端设备sim卡号
        byte[] simBytes = new byte[6];
        System.arraycopy(bytes, offset, simBytes, 0, simBytes.length);
        String deviceSn = IntercomMessageBuilder.bytes2bcd(simBytes);
        offset += simBytes.length;

        // 逻辑通道号
        int channelId = IntercomMessageBuilder.byte2int(bytes[offset++]);

        // 数据类型 + 分包处理标志 + 时间戳
        byte[] frameTime = new byte[9];
        System.arraycopy(bytes, offset, frameTime, 0, frameTime.length);
        offset += frameTime.length;

        // pcm数据长度
        byte[] frameLen = new byte[2];
        System.arraycopy(bytes, offset, frameLen, 0, frameLen.length);
        offset += frameLen.length;

        // pcm数据
        byte[] data = new byte[IntercomMessageBuilder.word2int(frameLen)];
        System.arraycopy(bytes, offset, data, 0, data.length);

        String gatewayId = (String) cacheManager.get(deviceSn + "_" + channelId + "_1");
        if (!StringUtils.isEmpty(gatewayId)) {
            IntercomMessageBuilder mb = new IntercomMessageBuilder();
            mb.append(frameHeader);
            mb.addByte(1);
            mb.addByte(simBytes.length);
            mb.append(simBytes);
            mb.addByte(channelId);
            mb.append(frameTime);
            mb.append(frameLen);
            mb.append(data);
            downstreamMessageProducer.send(gatewayId.concat("-intercom"), mb.getHexes());
        }
    }

}
