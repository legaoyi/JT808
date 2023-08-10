package com.legaoyi.iov.platform.service;

import java.util.List;
import java.util.Map;

public interface DeviceService {

    public Map<?, ?> getBySimCode(String simCode) throws Exception;

    public Map<?, ?> get(String id) throws Exception;

    public void delete(String id) throws Exception;

    public Map<?, ?> save(Map<?, ?> data) throws Exception;
    
    public List<?> findAll()  throws Exception;
    
    public void setOffline(String simCode) throws Exception;
    
    public void setOnline(String simCode) throws Exception;
}
