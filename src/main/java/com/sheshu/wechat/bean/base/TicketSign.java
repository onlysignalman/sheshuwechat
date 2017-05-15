package com.sheshu.wechat.bean.base;

/**
 * 微信Js接口签名
 * Created by 董帮辉 on 2017-5-14.
 */
public class TicketSign {

    //时间戳
    private String timestamp;

    //签名
    private String signature;

    //appId
    private String appId;

    //url
    private String url;

    //随机串
    private String noncestr;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }
}
