package com.legaoyi.iov.protocol.downstream.messagebody;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.DownstreamMessageBody;
import com.legaoyi.iov.protocol.message.MessageBody;

/*
 * 摄像头立即拍摄命令
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "8801" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_8801_MessageBody extends DownstreamMessageBody {

    private static final long serialVersionUID = -4356706548662314173L;

    public static final String MESSAGE_ID = "8801";

    /** 通道id **/
    @JsonProperty("channelId")
    private int channelId;

    /** 拍摄命令，0：表示停止拍摄，1：表示录像;其他拍照 **/
    @JsonProperty("commandType")
    private int commandType = 2;

    /** 拍摄张数 **/
    @JsonProperty("totalPicture")
    private int totalPicture;

    /** 拍摄间隔/录像时间 **/
    @JsonProperty("executionTime")
    private int executionTime;

    /** 保存标识 **/
    @JsonProperty("saveFlag")
    private int saveFlag;

    /** 分辨率 **/
    @JsonProperty("resolution")
    private int resolution = 1;

    /** 质量 **/
    @JsonProperty("quality")
    private int quality = 10;

    /** 亮度 **/
    @JsonProperty("luminance")
    private int luminance;

    /** 对比度 **/
    @JsonProperty("contrast")
    private int contrast;

    /** 饱和度 **/
    @JsonProperty("saturation")
    private int saturation;

    /** 色度 **/
    @JsonProperty("chromaticity")
    private int chromaticity;

    public final int getChannelId() {
        return channelId;
    }

    public final void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public final int getCommandType() {
        return commandType;
    }

    public final void setCommandType(int commandType) {
        this.commandType = commandType;
    }

    public final int getTotalPicture() {
        return totalPicture;
    }

    public final void setTotalPicture(int totalPicture) {
        this.totalPicture = totalPicture;
    }

    public final int getExecutionTime() {
        return executionTime;
    }

    public final void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public final int getSaveFlag() {
        return saveFlag;
    }

    public final void setSaveFlag(int saveFlag) {
        this.saveFlag = saveFlag;
    }

    public final int getResolution() {
        return resolution;
    }

    public final void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public final int getQuality() {
        return quality;
    }

    public final void setQuality(int quality) {
        this.quality = quality;
    }

    public final int getLuminance() {
        return luminance;
    }

    public final void setLuminance(int luminance) {
        this.luminance = luminance;
    }

    public final int getContrast() {
        return contrast;
    }

    public final void setContrast(int contrast) {
        this.contrast = contrast;
    }

    public final int getSaturation() {
        return saturation;
    }

    public final void setSaturation(int saturation) {
        this.saturation = saturation;
    }

    public final int getChromaticity() {
        return chromaticity;
    }

    public final void setChromaticity(int chromaticity) {
        this.chromaticity = chromaticity;
    }

}
