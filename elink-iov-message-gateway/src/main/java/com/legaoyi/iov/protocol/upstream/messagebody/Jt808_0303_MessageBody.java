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
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "0303" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_0303_MessageBody extends UpstreamMessageBody {

    private static final long serialVersionUID = 6961030326609766892L;

    public static final String MESSAGE_ID = "0303";

    /** 信息类型 **/
    @JsonProperty("type")
    private int type;

    /** 点播或取消标志 **/
    @JsonProperty("flag")
    private int flag;

    public final int getType() {
        return type;
    }

    public final void setType(int type) {
        this.type = type;
    }

    public final int getFlag() {
        return flag;
    }

    public final void setFlag(int flag) {
        this.flag = flag;
    }

}
