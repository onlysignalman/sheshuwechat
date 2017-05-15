package com.sheshu.wechat.exception;

import com.sheshu.wechat.bean.base.Error;

/**
 * 自定义微信异常
 * Created by 董帮辉 on 2017-5-14.
 */
public class ErrorException extends Exception {

    private Error error;

    public ErrorException(Error error){
        super(error.toString());
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
