package com.legaoyi.iov.protocol.upstream.messagebody;

import java.util.Map;

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
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "0104" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_0104_MessageBody extends UpstreamMessageBody {

    private static final long serialVersionUID = -3392914164582453750L;

    public static final String MESSAGE_ID = "0104";

    /** 应答流水号 **/
    @JsonProperty("messageSeq")
    private int messageSeq;

    /** 参数项列表 **/
    @JsonProperty("paramList")
    private Map<String, String> paramList;

    public final int getMessageSeq() {
        return messageSeq;
    }

    public final void setMessageSeq(int messageSeq) {
        this.messageSeq = messageSeq;
    }

    public final Map<String, String> getParamList() {
        return paramList;
    }

    public final void setParamList(Map<String, String> paramList) {
        this.paramList = paramList;
    }

}
