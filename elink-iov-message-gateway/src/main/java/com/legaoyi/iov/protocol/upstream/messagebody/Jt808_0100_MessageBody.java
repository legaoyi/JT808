package com.legaoyi.iov.protocol.upstream.messagebody;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.MessageBody;
import com.legaoyi.iov.protocol.messagebody.RegisterMessageBody;

/*
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "0100" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_0100_MessageBody extends RegisterMessageBody {

    private static final long serialVersionUID = -526107277912187732L;

    public static final String MESSAGE_ID = "0100";

    /** 省域id **/
    @JsonProperty("provinceId")
    private int provinceId;

    /** 市县id **/
    @JsonProperty("cityId")
    private int cityId;

    /**
     * 制造商id
     */
    @JsonProperty("mfrsId")
    private String mfrsId;

    /** 终端型号 **/
    @JsonProperty("terminalType")
    private String terminalType;

    /** 车牌颜色 **/
    @JsonProperty("vinColor")
    private int vinColor;

    /** 车辆标识 **/
    @JsonProperty("vin")
    private String vin;

    public final int getProvinceId() {
        return provinceId;
    }

    public final void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public final int getCityId() {
        return cityId;
    }

    public final void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public final String getMfrsId() {
        return mfrsId;
    }

    public final void setMfrsId(String mfrsId) {
        this.mfrsId = mfrsId;
    }

    public final String getTerminalType() {
        return terminalType;
    }

    public final void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public final int getVinColor() {
        return vinColor;
    }

    public final void setVinColor(int vinColor) {
        this.vinColor = vinColor;
    }

    public final String getVin() {
        return vin;
    }

    public final void setVin(String vin) {
        this.vin = vin;
    }

}
