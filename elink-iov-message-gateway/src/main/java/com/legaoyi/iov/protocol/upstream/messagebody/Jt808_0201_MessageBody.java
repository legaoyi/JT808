package com.legaoyi.iov.protocol.upstream.messagebody;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.MessageBody;
import com.legaoyi.iov.protocol.message.UpstreamMessageBody;

/*
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "0201" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_0201_MessageBody extends UpstreamMessageBody {

    private static final long serialVersionUID = 1258632212412669618L;

    public static final String MESSAGE_ID = "0201";

    /** 应答流水号 **/
    @JsonProperty("messageSeq")
    private int messageSeq;

    /** 位置信息 **/
    @JsonProperty("gpsInfo")
    private Jt808_0200_MessageBody gpsInfo;

    public final int getMessageSeq() {
        return messageSeq;
    }

    public final void setMessageSeq(int messageSeq) {
        this.messageSeq = messageSeq;
    }

    public final Jt808_0200_MessageBody getGpsInfo() {
        return gpsInfo;
    }

    public final void setGpsInfo(Jt808_0200_MessageBody gpsInfo) {
        this.gpsInfo = gpsInfo;
    }

}
