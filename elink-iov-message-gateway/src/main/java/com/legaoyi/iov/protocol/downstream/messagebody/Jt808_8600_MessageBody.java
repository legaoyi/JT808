package com.legaoyi.iov.protocol.downstream.messagebody;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.DownstreamMessageBody;
import com.legaoyi.iov.protocol.message.MessageBody;

/*
 * 设置圆形区域
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "8600" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_8600_MessageBody extends DownstreamMessageBody {

    private static final long serialVersionUID = 5704873311863152764L;

    public static final String MESSAGE_ID = "8600";

    /** 设置属性 **/
    @JsonProperty("type")
    private int type = 1;

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
     * map.put("lng", 39.3d);//中心点纬度<br/>
     * map.put("lat", 116.3d);//中心点经度<br/>
     * map.put("radius", 1000);//半径<br/>
     * 
     **/
    @JsonProperty("area")
    private Map<String, Object> area;

    public final int getType() {
        return type;
    }

    public final void setType(int type) {
        this.type = type;
    }

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

    public final Map<String, Object> getArea() {
        return area;
    }

    public final void setArea(Map<String, Object> area) {
        this.area = area;
    }

}
