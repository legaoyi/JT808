package com.legaoyi.iov.message.processor.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler
    public Result exceptionHandler(Exception e) {
        logger.error("exceptionHandler", e);
        String errorMsg;
        if (e.getCause() != null) {
            errorMsg = e.getCause().getMessage();
        } else {
            errorMsg = e.getLocalizedMessage();
            if (errorMsg == null || "".equals(errorMsg)) {
                errorMsg = e.getMessage();
            }
        }
        return new Result(Result.RESP_CODE_ERROR, errorMsg);
    }
}
