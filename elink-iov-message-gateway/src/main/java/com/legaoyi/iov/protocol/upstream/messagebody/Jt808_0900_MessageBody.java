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
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "0900" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_0900_MessageBody extends UpstreamMessageBody {

    private static final long serialVersionUID = -1986567856942950100L;

    public static final String MESSAGE_ID = "0900";

    /** 透传消息类型 **/
    @JsonProperty("messageType")
    private String messageType;

    /** 透传消息内容 **/
    @JsonProperty("message")
    private String message;

    public final String getMessageType() {
        return messageType;
    }

    public final void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public final String getMessage() {
        return message;
    }

    public final void setMessage(String message) {
        this.message = message;
    }

}
