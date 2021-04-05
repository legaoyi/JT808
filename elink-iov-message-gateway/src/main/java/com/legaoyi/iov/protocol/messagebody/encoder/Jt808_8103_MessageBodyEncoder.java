package com.legaoyi.iov.protocol.messagebody.encoder;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.downstream.messagebody.Jt808_8103_MessageBody;
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
@Component(MessageBodyEncoder.MESSAGE_BODY_ENCODER_BEAN_PREFIX + "8103" + MessageBodyEncoder.MESSAGE_BODY_ENCODER_BEAN_SUFFIX)
public class Jt808_8103_MessageBodyEncoder implements MessageBodyEncoder {

    @Override
    public byte[] encode(MessageBody message) throws IllegalMessageException {
        try {
            Jt808_8103_MessageBody messageBody = (Jt808_8103_MessageBody) message;
            Map<String, String> paramList = messageBody.getParamList();
            MessageBuilder mb = new MessageBuilder();
            mb.addByte(paramList.size());
            for (String key : paramList.keySet()) {
                String val = paramList.get(key);
                String type = paramTypeMap.get(key);
                byte[] bytes = null;
                if ("DWORD".equals(type)) {
                    bytes = ByteUtils.hex2dword(val);
                } else if ("WORD".equals(type)) {
                    bytes = ByteUtils.hex2word(val);
                } else if ("STRING".equals(type)) {
                    bytes = ByteUtils.gbk2bytes(val);
                } else if ("BYTE".equals(type)) {
                    bytes = ByteUtils.hex2bytes(val);
                } else {
                    bytes = ByteUtils.hex2bytes(val);
                }
                mb.append(ByteUtils.hex2dword(key));
                mb.addByte(bytes.length);
                mb.append(bytes);
            }
            return mb.getBytes();
        } catch (Exception e) {
            throw new IllegalMessageException(e);
        }
    }

    public static final Map<String, String> paramTypeMap = new HashMap<String, String>();

    static {
        paramTypeMap.put("0001", "DWORD");
        paramTypeMap.put("0002", "DWORD");
        paramTypeMap.put("0003", "DWORD");
        paramTypeMap.put("0004", "DWORD");
        paramTypeMap.put("0005", "DWORD");
        paramTypeMap.put("0006", "DWORD");
        paramTypeMap.put("0007", "DWORD");
        paramTypeMap.put("0010", "STRING");
        paramTypeMap.put("0011", "STRING");
        paramTypeMap.put("0012", "STRING");
        paramTypeMap.put("0013", "STRING");
        paramTypeMap.put("0014", "STRING");
        paramTypeMap.put("0015", "STRING");
        paramTypeMap.put("0016", "STRING");
        paramTypeMap.put("0017", "STRING");
        paramTypeMap.put("0018", "DWORD");
        paramTypeMap.put("0019", "DWORD");
        paramTypeMap.put("0020", "DWORD");
        paramTypeMap.put("0021", "DWORD");
        paramTypeMap.put("0022", "DWORD");
        paramTypeMap.put("0027", "DWORD");
        paramTypeMap.put("0028", "DWORD");
        paramTypeMap.put("0029", "DWORD");
        paramTypeMap.put("0030", "DWORD");
        paramTypeMap.put("002C", "DWORD");
        paramTypeMap.put("002D", "DWORD");
        paramTypeMap.put("002E", "DWORD");
        paramTypeMap.put("002F", "DWORD");
        paramTypeMap.put("0030", "DWORD");
        paramTypeMap.put("0031", "WORD");
        paramTypeMap.put("0040", "STRING");
        paramTypeMap.put("0041", "STRING");
        paramTypeMap.put("0042", "STRING");
        paramTypeMap.put("0043", "STRING");
        paramTypeMap.put("0044", "STRING");
        paramTypeMap.put("0045", "DWORD");
        paramTypeMap.put("0046", "DWORD");
        paramTypeMap.put("0047", "DWORD");
        paramTypeMap.put("0048", "STRING");
        paramTypeMap.put("0049", "STRING");
        paramTypeMap.put("0050", "DWORD");
        paramTypeMap.put("0051", "DWORD");
        paramTypeMap.put("0052", "DWORD");
        paramTypeMap.put("0053", "DWORD");
        paramTypeMap.put("0054", "DWORD");
        paramTypeMap.put("0055", "DWORD");
        paramTypeMap.put("0056", "DWORD");
        paramTypeMap.put("0057", "DWORD");
        paramTypeMap.put("0058", "DWORD");
        paramTypeMap.put("0059", "DWORD");
        paramTypeMap.put("005A", "DWORD");
        paramTypeMap.put("005B", "WORD");
        paramTypeMap.put("005C", "WORD");
        paramTypeMap.put("005D", "WORD");
        paramTypeMap.put("005E", "WORD");
        paramTypeMap.put("0064", "DWORD");
        paramTypeMap.put("0065", "DWORD");
        paramTypeMap.put("0070", "DWORD");
        paramTypeMap.put("0071", "DWORD");
        paramTypeMap.put("0072", "DWORD");
        paramTypeMap.put("0073", "DWORD");
        paramTypeMap.put("0074", "DWORD");
        paramTypeMap.put("0080", "DWORD");
        paramTypeMap.put("0081", "WORD");
        paramTypeMap.put("0082", "WORD");
        paramTypeMap.put("0083", "STRING");
        paramTypeMap.put("0084", "BYTE");
        paramTypeMap.put("0090", "BYTE");
        paramTypeMap.put("0091", "BYTE");
        paramTypeMap.put("0092", "BYTE");
        paramTypeMap.put("0093", "DWORD");
        paramTypeMap.put("0094", "BYTE");
        paramTypeMap.put("0095", "DWORD");
        paramTypeMap.put("0100", "DWORD");
        paramTypeMap.put("0101", "WORD");
        paramTypeMap.put("0102", "DWORD");
        paramTypeMap.put("0103", "WORD");
    }
}
