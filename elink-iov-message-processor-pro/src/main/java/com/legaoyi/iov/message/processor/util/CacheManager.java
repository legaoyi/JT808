package com.legaoyi.iov.message.processor.util;

import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-04-15
 */
@Component("iovCacheManager")
public class CacheManager {

    private static final String CACHE_NAME_ELINK_IOV_CACHE = "elink_iov_cache";

    @CachePut(value = CACHE_NAME_ELINK_IOV_CACHE, key = "#key")
    public Object add(String key, Object val) {
        return val;
    }

    @Cacheable(value = CACHE_NAME_ELINK_IOV_CACHE, key = "#key", unless = "#result == null")
    public Object get(String key) {
        return null;
    }

    @CacheEvict(value = CACHE_NAME_ELINK_IOV_CACHE, key = "#key")
    public void remove(String key) {}

    @CachePut(value = CACHE_NAME_ELINK_IOV_CACHE, key = "'device_'+#deviceSn")
    public Map<String, Object> addDevice(String deviceSn, Map<String, Object> device) {
        return device;
    }

    @Cacheable(value = CACHE_NAME_ELINK_IOV_CACHE, key = "'device_'+#deviceSn", unless = "#result == null")
    public Map<String, Object> getDevice(String deviceSn) {
        return null;
    }
}
