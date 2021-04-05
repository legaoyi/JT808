package com.legaoyi.iov.protocol.downstream.messagebody;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.DownstreamMessageBody;
import com.legaoyi.iov.protocol.message.MessageBody;

/*
 * 信息服务
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "8304" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_8304_MessageBody extends DownstreamMessageBody {

    private static final long serialVersionUID = -3441467355858813877L;

    public static final String MESSAGE_ID = "8304";

    /** 信息类型 **/
    @JsonProperty("type")
    private int type;

    /** 信息内容 **/
    @JsonProperty("info")
    private String info;

    public final int getType() {
        return type;
    }

    public final void setType(int type) {
        this.type = type;
    }

    public final String getInfo() {
        return info;
    }

    public final void setInfo(String info) {
        this.info = info;
    }

}
