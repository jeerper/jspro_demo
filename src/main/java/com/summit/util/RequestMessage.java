package com.summit.util;

import org.springframework.security.core.parameters.P;

/**
 * @author: jeerper
 * @since: 2020/12/24 15:05
 */
public class RequestMessage<T> {
    /**
     * 返回成功的状态码
     */
    public static String SUCCESS_GETS_CODE="200";
    public static String SUCCESS_GETS_MSG="ok";
    /**
     * 保存或新建成功的状态码
     */
    public static String SUCCESS_POSTS_CODE="201";
    public static String SUCCESS_POSTS_MSG="ok";
    /**
     * 删除成功的状态码
     */
    public static String SUCCESS_DELETES_CODE="204";
    public static String SUCCESS_DELETES_MSG="ok";
    /**
     * 服务器发生错误的状态码
     */
    public static String ERROR_CODE="500";
    public static String ERROR_MSG="error";

    private String code;
    private String message;
    private String error;
    private Object data;
    private int current;
    private int size;
    private int total;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
