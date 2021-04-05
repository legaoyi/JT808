package com.legaoyi.iov.protocol.downstream.messagebody;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.DownstreamMessageBody;
import com.legaoyi.iov.protocol.message.MessageBody;

/*
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "8802" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_8802_MessageBody extends DownstreamMessageBody {

    private static final long serialVersionUID = -3248764223265983906L;

    public static final String MESSAGE_ID = "8802";

    /** 多媒体类型 **/
    @JsonProperty("mediaType")
    private int mediaType;

    /** 事件项编码 **/
    @JsonProperty("eventCode")
    private int eventCode;

    /** 通道id **/
    @JsonProperty("channelId")
    private int channelId;

    /** 起始时间 **/
    @JsonProperty("startTime")
    private String startTime;

    /** 结束时间 **/
    @JsonProperty("endTime")
    private String endTime;

    public final int getMediaType() {
        return mediaType;
    }

    public final void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public final int getEventCode() {
        return eventCode;
    }

    public final void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public final int getChannelId() {
        return channelId;
    }

    public final void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public final String getStartTime() {
        return startTime;
    }

    public final void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public final String getEndTime() {
        return endTime;
    }

    public final void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}
