package com.legaoyi.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2015-01-30
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private JsonUtil() {}

    public static <T> T convertStringToObject(String src, Class<T> returnClass) {
        try {
            return objectMapper.readValue(src, returnClass);
        } catch (Exception e) {
            logger.error("convertStringToObject error,arg={}, info={}", src, e);
        }
        return null;
    }

    public static <T> String covertObjectToString(T t) {
        try {
            return objectMapper.writeValueAsString(t);
        } catch (Exception e) {
            logger.error("covertObjectToString error,arg={}, info={}", t, e);
        }
        return null;
    }

    public static <T> String covertObjectToStringWithoutNull(T t) {
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return objectMapper.writeValueAsString(t);
        } catch (Exception e) {
            logger.error("covertObjectToStringWithoutNull error,arg={}, info={}", t, e);
        }
        return null;
    }

    public static <T> String covertObjectToStringWithoutEmpty(T t) {
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            return objectMapper.writeValueAsString(t);
        } catch (Exception e) {
            logger.error("covertObjectToStringWithoutEmpty error,arg={}, info={}", t, e);
        }
        return null;
    }
}
