package com.legaoyi.iov.platform.web.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Result implements java.io.Serializable {

    private static final long serialVersionUID = 3745456279954252934L;
    
    public static final short RESP_CODE_SYSTEM_ERROR = -1;

    public static final short RESP_CODE_SUCCESS = 0;

    public static final short RESP_CODE_ERROR = 1;

    private int code = RESP_CODE_SUCCESS;

    private String message = "success";

    private Object data;

    public Result() {}

    public Result(Object data) {
        this.data = data;
    }

    public Result(int code, String message, Object data) {
        this(data);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public final String getMessage() {
        return message;
    }

    public final void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
