package com.legaoyi.iov.platform.service;

import java.util.List;
import java.util.Map;

public interface GpsService {

    public void save(Map<?, ?> data) throws Exception;

    public Map<?,?>  getLastGpsBySimCode(String simCode) throws Exception;

    public List<?>  getLastGps() throws Exception;
    
    public List<?> find(String simCode, long startTime, long endTime) throws Exception;
}
