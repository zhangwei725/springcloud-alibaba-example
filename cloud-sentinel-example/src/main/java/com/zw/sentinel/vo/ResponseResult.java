package com.zw.sentinel.vo;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Data
public class ResponseResult<T> {
    private int status;
    private String msg;
    private T data;

}
