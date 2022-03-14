package com.legaoyi.iov.message.processor.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.common.util.JsonUtil;

/**
 * 业务系统与网关的交互消息
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2015-01-30
 */
public class ExchangeMessage implements Serializable {

    private static final long serialVersionUID = -5632679084054473414L;

    /** 控制消息 **/
    public static final String MESSAGEID_CONTROL_MESSAGE = "1000";

    /** 上行消息 **/
    public static final String MESSAGEID_UPSTREAM_MESSAGE = "0000";

    /** 下行消息 **/
    public static final String MESSAGEID_DOWNSTREAM_MESSAGE = "8000";

    /** 状态消息 **/
    public static final String MESSAGEID_STATUS_MESSAGE = "0002";

    /** 终端鉴权时平台鉴权结果反馈消息，仅与网关交互 **/
    public static final String MESSAGEID_PLATFORM_AUTH_RESP_MESSAGE = "8102";

    /** 平台注销终端下发的消息 **/
    public static final String MESSAGEID_DEVICE_LOGOFF = "8003";

    /** 终端上传的多媒体消息 **/
    public static final String MESSAGEID_UPSTREAM_MEDIA_MESSAGE = "0801";

    /** 网关响应平台下发消息的反馈结果 **/
    public static final String MESSAGEID_GATEWAY_RESP_MESSAGE = "0001";

    /** 网关向平台发送终端链路状态消息 **/
    public static final String MESSAGEID_GATEWAY_LINK_STATUS_MESSAGE = "0002";

    /** 网关流量控制告警消息 **/
    public static final String MESSAGEID_DATA_LIMIT_ALARM = "0008";

    /** 网关向平台发送网关重启消息 **/
    public static final String MESSAGEID_GATEWAY_RESTART = "0009";

    /** 解除黑名单 消息 **/
    public static final String MESSAGEID_PLATFORM_DOWN_REMOVE_BLACK_LIST_MESSAGE = "110";

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
