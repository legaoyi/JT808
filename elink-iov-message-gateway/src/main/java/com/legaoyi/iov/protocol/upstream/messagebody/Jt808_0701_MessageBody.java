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
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "0701" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_0701_MessageBody extends UpstreamMessageBody {

    private static final long serialVersionUID = 2360483747739133170L;

    public static final String MESSAGE_ID = "0701";

    /** 电子运单内容 **/
    @JsonProperty("waybill")
    private String waybill;

    public final String getWaybill() {
        return waybill;
    }

    public final void setWaybill(String waybill) {
        this.waybill = waybill;
    }

}
