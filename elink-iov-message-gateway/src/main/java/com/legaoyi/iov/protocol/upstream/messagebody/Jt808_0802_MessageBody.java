package com.legaoyi.iov.protocol.upstream.messagebody;

import java.io.Serializable;
import java.util.List;

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
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "0802" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_0802_MessageBody extends UpstreamMessageBody {

    private static final long serialVersionUID = 2682604275836450908L;

    public static final String MESSAGE_ID = "0802";

    /** 应答流水号 **/
    @JsonProperty("messageSeq")
    private int messageSeq;

    /** 数据检索项 **/
    @JsonProperty("itemList")
    private List<Multimedia> itemList;

    public final int getMessageSeq() {
        return messageSeq;
    }

    public final void setMessageSeq(int messageSeq) {
        this.messageSeq = messageSeq;
    }

    public final List<Multimedia> getItemList() {
        return itemList;
    }

    public final void setItemList(List<Multimedia> itemList) {
        this.itemList = itemList;
    }

    public class Multimedia implements Serializable {

        private static final long serialVersionUID = 943196043911749512L;

        /** 多媒体id **/
        @JsonProperty("mediaDataId")
        private Long mediaDataId;

        /** 多媒体类型 **/
        @JsonProperty("mediaType")
        private int mediaType;

        /** 通道id **/
        @JsonProperty("channelId")
        private int channelId;

        /** 多媒体格式类型 **/
        @JsonProperty("mediaFormatCode")
        private int mediaFormatCode;

        /** 位置信息 **/
        @JsonProperty("gpsInfo")
        private MessageBody gpsInfo;

        public final Long getMediaDataId() {
            return mediaDataId;
        }

        public final void setMediaDataId(Long mediaDataId) {
            this.mediaDataId = mediaDataId;
        }

        public final int getMediaType() {
            return mediaType;
        }

        public final void setMediaType(int mediaType) {
            this.mediaType = mediaType;
        }

        public final int getChannelId() {
            return channelId;
        }

        public final void setChannelId(int channelId) {
            this.channelId = channelId;
        }

        public final int getMediaFormatCode() {
            return mediaFormatCode;
        }

        public final void setMediaFormatCode(int mediaFormatCode) {
            this.mediaFormatCode = mediaFormatCode;
        }

        public final MessageBody getGpsInfo() {
            return gpsInfo;
        }

        public final void setGpsInfo(MessageBody gpsInfo) {
            this.gpsInfo = gpsInfo;
        }

    }
}
