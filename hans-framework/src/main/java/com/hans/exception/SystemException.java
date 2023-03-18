package com.hans.exception;

import com.hans.enums.AppHttpCodeEnum;
import lombok.Data;

@Data
public class SystemException extends RuntimeException{

    private int code;

    private String msg;


    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

}
