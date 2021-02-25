package com.zw.oss.result;

import lombok.Data;

/**
 * @author zhangwei
 */
@Data
public class ResponseResult<T> {
    private String msg;
    private int status;
    private T data;

    public static <T> ResponseResult<T> success(T data) {
        return success(200, "success", data);
    }

    public static <T> ResponseResult<T> success(int status, String msg, T data) {
        ResponseResult<T> response = new ResponseResult<>();
        response.setMsg(msg);
        response.setStatus(status);
        response.setData(data);
        return response;
    }

}
