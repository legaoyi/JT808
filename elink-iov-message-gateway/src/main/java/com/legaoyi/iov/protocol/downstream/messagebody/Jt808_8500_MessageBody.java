package com.legaoyi.iov.protocol.downstream.messagebody;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.DownstreamMessageBody;
import com.legaoyi.iov.protocol.message.MessageBody;

/*
 * 车辆控制
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "8500" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_8500_MessageBody extends DownstreamMessageBody {

    private static final long serialVersionUID = -8541153336745142425L;

    public static final String MESSAGE_ID = "8500";

    /** 控制标志 **/
    @JsonProperty("flag")
    private int flag;

    public final int getFlag() {
        return flag;
    }

    public final void setFlag(int flag) {
        this.flag = flag;
    }

}
