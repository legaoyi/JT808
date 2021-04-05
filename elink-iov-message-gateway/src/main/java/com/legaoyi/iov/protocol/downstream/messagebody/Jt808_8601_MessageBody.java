package com.legaoyi.iov.protocol.downstream.messagebody;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.DownstreamMessageBody;
import com.legaoyi.iov.protocol.message.MessageBody;

/*
 * 删除圆形区域
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "8601" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_8601_MessageBody extends DownstreamMessageBody {

    private static final long serialVersionUID = 1086124614660196215L;

    public static final String MESSAGE_ID = "8601";

    /** 区域id列表 **/
    @JsonProperty("areaIds")
    private List<Long> areaIds;

    public final List<Long> getAreaIds() {
        return areaIds;
    }

    public final void setAreaIds(List<Long> areaIds) {
        this.areaIds = areaIds;
    }

}
