package com.legaoyi.iov.message.processor.service;

import java.util.Map;

public interface MessageService {

    public Map<String, Object> send(String deviceSn, String messageId, Map<String, Object> messageBody) throws Exception;
    
    public Map<String, Object> startLive(String deviceSn, Map<String, Object> messageBody) throws Exception;
    
    public Map<String, Object> stopLive(String deviceSn, Map<String, Object> messageBody) throws Exception;
    
    public void sendIntercomData(byte[] bytes) throws Exception;
    
    /***
     * 旧版对讲格式
     * @param bytes
     * @throws Exception
     */
    public void sendIntercomData1(byte[] bytes) throws Exception;
}
