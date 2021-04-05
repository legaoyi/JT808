package com.legaoyi.iov.protocol.downstream.messagebody;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.DownstreamMessageBody;
import com.legaoyi.iov.protocol.message.MessageBody;

/*
 * 信息点播设置
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "8303" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_8303_MessageBody extends DownstreamMessageBody {

    private static final long serialVersionUID = 3146512177315464175L;

    public static final String MESSAGE_ID = "8303";

    /** 设置类型 **/
    @JsonProperty("type")
    private int type;

    /** 信息项列表,key/val键值对，key包括信息类型：infoType,信息名称：infoName **/
    @JsonProperty("infoList")
    private List<Map<String, Object>> infoList;

    public final int getType() {
        return type;
    }

    public final void setType(int type) {
        this.type = type;
    }

    public final List<Map<String, Object>> getInfoList() {
        return infoList;
    }

    public final void setInfoList(List<Map<String, Object>> infoList) {
        this.infoList = infoList;
    }

}
