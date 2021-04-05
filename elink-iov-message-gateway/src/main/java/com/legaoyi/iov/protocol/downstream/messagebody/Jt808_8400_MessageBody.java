package com.legaoyi.iov.protocol.downstream.messagebody;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.DownstreamMessageBody;
import com.legaoyi.iov.protocol.message.MessageBody;

/*
 * 电话回拨
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "8400" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_8400_MessageBody extends DownstreamMessageBody {

    private static final long serialVersionUID = 5127626035972934474L;

    public static final String MESSAGE_ID = "8400";

    /** 回拨类型 **/
    @JsonProperty("type")
    private int type;

    /** 回拨电话号码 **/
    @JsonProperty("tel")
    private String tel;

    public final int getType() {
        return type;
    }

    public final void setType(int type) {
        this.type = type;
    }

    public final String getTel() {
        return tel;
    }

    public final void setTel(String tel) {
        this.tel = tel;
    }

}
