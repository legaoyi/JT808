package com.legaoyi.storer.message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.common.util.JsonUtil;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;gaoshengbo@legaoyi.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-08-18
 */
public class ExchangeMessage implements Serializable {

    private static final long serialVersionUID = -5632679084054473414L;

    /** 终端鉴权时平台鉴权结果反馈消息，仅与网关交互 **/
    public static final String MESSAGEID_PLATFORM_AUTH_RESP_MESSAGE = "8102";

    /** 平台下发的消息 **/
    public static final String MESSAGEID_PLATFORM_DOWN_MESSAGE = "8000";

    /** 终端上传的消息 **/
    public static final String MESSAGEID_GATEWAY_UP_MESSAGE = "0000";
    
    /** 网关向平台发送终端链路状态消息 **/
	public static final String MESSAGEID_GATEWAY_LINK_STATUS_MESSAGE = "0002";
	
	/** 网关响应平台下发消息的反馈结果 **/
	public static final String MESSAGEID_GATEWAY_RESP_MESSAGE = "0001";


    @JsonProperty("exchangeId")
    private String exchangeId;

    @JsonProperty("messageId")
    private String messageId;

    @JsonProperty("gatewayId")
    private String gatewayId;

    @JsonProperty("message")
    private Object message;

    @JsonProperty("createTime")
    private long createTime;

    @JsonIgnore
    private Map<String, Object> extAttribute = new HashMap<String, Object>();

    public ExchangeMessage() {}

    public ExchangeMessage(String messageId, Object message, String exchangeId) {
        this.messageId = messageId;
        this.message = message;
        this.exchangeId = exchangeId;
        this.createTime = System.currentTimeMillis();
    }

    public ExchangeMessage(String messageId, Object message, String exchangeId, String gatewayId) {
        this.messageId = messageId;
        this.message = message;
        this.exchangeId = exchangeId;
        this.gatewayId = gatewayId;
        this.createTime = System.currentTimeMillis();
    }

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Object getExtAttribute(String key) {
        return extAttribute.get(key);
    }

    public void putExtAttribute(String key, Object value) {
        extAttribute.put(key, value);
    }

    public Map<String, Object> getExtAttribute() {
        return extAttribute;
    }

    public void setExtAttribute(Map<String, Object> extAttribute) {
        this.extAttribute = extAttribute;
    }

    public String toString() {
        return JsonUtil.covertObjectToStringWithoutNull(this);
    }
}
