package com.legaoyi.iov.protocol.downstream.messagebody;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.DownstreamMessageBody;
import com.legaoyi.iov.protocol.message.MessageBody;

/*
 * 多媒体数据上传应答
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "8800" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_8800_MessageBody extends DownstreamMessageBody {

    private static final long serialVersionUID = -8731742875831827638L;

    public static final String MESSAGE_ID = "8800";

    /** 多媒体id **/
    @JsonProperty("mediaDataId")
    private long mediaDataId;

    /** 重传包id列表 **/
    @JsonProperty("resendPackgeList")
    private List<Integer> resendPackgeList;

    public final long getMediaDataId() {
        return mediaDataId;
    }

    public final void setMediaDataId(long mediaDataId) {
        this.mediaDataId = mediaDataId;
    }

    public final List<Integer> getResendPackgeList() {
        return resendPackgeList;
    }

    public final void setResendPackgeList(List<Integer> resendPackgeList) {
        this.resendPackgeList = resendPackgeList;
    }

}
