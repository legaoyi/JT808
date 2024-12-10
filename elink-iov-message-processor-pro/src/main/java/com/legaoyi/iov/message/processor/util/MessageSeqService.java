package com.legaoyi.iov.message.processor.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("messageSeqService")
public class MessageSeqService {

    @Autowired
    @Qualifier("iovCacheManager")
    private CacheManager cacheManager;

    private static final int MAX_MESSAGE_SEQ = 65535;

    public int next(String deviceSn, String messageId) {
        synchronized (deviceSn) {
            String cacheKey = deviceSn + "_" + messageId;
            Integer seq = (Integer) cacheManager.get(cacheKey);
            if (seq == null || seq == MAX_MESSAGE_SEQ) {
                seq = 0;
            }
            seq++;
            cacheManager.add(cacheKey, seq);
            return seq;
        }
    }
}
