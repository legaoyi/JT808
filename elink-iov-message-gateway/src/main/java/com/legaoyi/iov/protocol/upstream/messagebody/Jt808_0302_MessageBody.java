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
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "0302" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_0302_MessageBody extends UpstreamMessageBody {

    private static final long serialVersionUID = 1580559134005988215L;

    public static final String MESSAGE_ID = "0302";

    /** 应答流水号 **/
    @JsonProperty("messageSeq")
    private int messageSeq;

    /** 答案id **/
    @JsonProperty("answerId")
    private int answerId;

    public final int getMessageSeq() {
        return messageSeq;
    }

    public final void setMessageSeq(int messageSeq) {
        this.messageSeq = messageSeq;
    }

    public final int getAnswerId() {
        return answerId;
    }

    public final void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

}
