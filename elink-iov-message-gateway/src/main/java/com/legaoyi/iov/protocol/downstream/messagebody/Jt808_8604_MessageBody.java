package com.legaoyi.iov.protocol.downstream.messagebody;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.DownstreamMessageBody;
import com.legaoyi.iov.protocol.message.MessageBody;

/*
 * 设置多边形区域
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "8604" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_8604_MessageBody extends DownstreamMessageBody {

    private static final long serialVersionUID = 248276715427525407L;

    public static final String MESSAGE_ID = "8604";

    /** 区域id **/
    @JsonProperty("areaId")
    private long areaId;

    /** 区域属性 **/
    @JsonProperty("attribute")
    private int attribute;

    /** 起始时间 **/
    @JsonProperty("startTime")
    private String startTime;

    /** 结束时间 **/
    @JsonProperty("endTime")
    private String endTime;

    /** 最高速度 **/
    @JsonProperty("limitedSpeed")
    private int limitedSpeed;

    /** 超速持续时间 **/
    @JsonProperty("durationTime")
    private int durationTime;

    /**
     * 顶点项列表，如 List<Map<String,Double>> vertexList = new ArrayList<Map<String,Double>>();<br/>
     * Map<String,Double> map =new HashMap<String,Double>();<br/>
     * map.put("lng", 39.58d);<br/>
     * map.put("lat", 116.05678d);<br/>
     * vertexList.add(map);<br/>
     **/
    @JsonProperty("vertexList")
    private List<Map<String, Double>> vertexList;

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

    public final int getLimitedSpeed() {
        return limitedSpeed;
    }

    public final void setLimitedSpeed(int limitedSpeed) {
        this.limitedSpeed = limitedSpeed;
    }

    public final int getDurationTime() {
        return durationTime;
    }

    public final void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

    public final List<Map<String, Double>> getVertexList() {
        return vertexList;
    }

    public final void setVertexList(List<Map<String, Double>> vertexList) {
        this.vertexList = vertexList;
    }

}
