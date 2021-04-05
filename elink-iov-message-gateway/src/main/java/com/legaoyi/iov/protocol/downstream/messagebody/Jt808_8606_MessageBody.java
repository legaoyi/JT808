package com.legaoyi.iov.protocol.downstream.messagebody;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.DownstreamMessageBody;
import com.legaoyi.iov.protocol.message.MessageBody;

/*
 * 设置路线
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "8606" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_8606_MessageBody extends DownstreamMessageBody {

    private static final long serialVersionUID = 8801280437560171104L;

    public static final String MESSAGE_ID = "8606";

    /** 区域id **/
    @JsonProperty("areaId")
    private long areaId;

    /** 路线属性 **/
    @JsonProperty("attribute")
    private int attribute;

    /** 起始时间,格式如2014-12-22 23:00:02 **/
    @JsonProperty("startTime")
    private String startTime;

    /** 结束时间，如2014-12-28 23:00:02 **/
    @JsonProperty("endTime")
    private String endTime;

    @JsonProperty("roadList")
    private List<Map<String, Object>> roadList;

    public final long getAreaId() {
        return areaId;
    }

    public final void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public final int getAttribute() {
        return attribute;
    }

    public final void setAttribute(int attribute) {
        this.attribute = attribute;
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

    public final List<Map<String, Object>> getRoadList() {
        return roadList;
    }

    public final void setRoadList(List<Map<String, Object>> roadList) {
        this.roadList = roadList;
    }

}
