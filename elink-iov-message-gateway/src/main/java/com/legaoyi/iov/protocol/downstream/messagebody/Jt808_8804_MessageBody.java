package com.legaoyi.iov.protocol.downstream.messagebody;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.DownstreamMessageBody;
import com.legaoyi.iov.protocol.message.MessageBody;

/*
 * 录音开始命令
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "8804" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_8804_MessageBody extends DownstreamMessageBody {

    private static final long serialVersionUID = -2544148330741996221L;

    public static final String MESSAGE_ID = "8804";

    /** 录音命令 **/
    @JsonProperty("commandType")
    private int commandType;

    /** 录音时间 **/
    @JsonProperty("executionTime")
    private int executionTime;

    /** 保存标识 **/
    @JsonProperty("saveFlag")
    private int saveFlag;

    /** 音频采样频率 **/
    @JsonProperty("samplingRate")
    private int samplingRate;

    public final int getCommandType() {
        return commandType;
    }

    public final void setCommandType(int commandType) {
        this.commandType = commandType;
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

    public final int getSamplingRate() {
        return samplingRate;
    }

    public final void setSamplingRate(int samplingRate) {
        this.samplingRate = samplingRate;
    }

}
