package com.sheshu.wechat.bean.base;

/**
 * Created by 董帮辉 on 2017-5-14.
 */
public class Error {

    private Integer errcode;

    private String errmsg;

    private String invalid;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }
}
