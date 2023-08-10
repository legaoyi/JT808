package com.legaoyi.iov.platform.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author gaoshengbo
 */
public class BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler
    public Result exceptionHandler(Exception e) {
        String errorMsg;
        if (e.getCause() != null) {
            errorMsg = e.getCause().getMessage();
        } else {
            errorMsg = e.getLocalizedMessage();
            if (errorMsg == null || "".equals(errorMsg)) {
                errorMsg = e.getMessage();
            }
        }
        logger.error("", e);
        return new Result(Result.RESP_CODE_ERROR, errorMsg, null);
    }
}
