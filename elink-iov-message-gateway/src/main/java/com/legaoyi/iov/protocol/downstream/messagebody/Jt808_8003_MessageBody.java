package com.legaoyi.iov.protocol.downstream.messagebody;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.DownstreamMessageBody;
import com.legaoyi.iov.protocol.message.MessageBody;

/*
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "8003" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_8003_MessageBody extends DownstreamMessageBody {

    private static final long serialVersionUID = -2901228399265832241L;

    public static final String MESSAGE_ID = "8003";

    /** 原始消息流水号 **/
    @JsonProperty("messageSeq")
    private int messageSeq;

    /** 重传包id列表 **/
    @JsonProperty("packageIds")
    private List<Integer> packageIds;

    public final int getMessageSeq() {
        return messageSeq;
    }

    public final void setMessageSeq(int messageSeq) {
        this.messageSeq = messageSeq;
    }

    public final List<Integer> getPackageIds() {
        return packageIds;
    }

    public final void setPackageIds(List<Integer> packageIds) {
        this.packageIds = packageIds;
    }

}
