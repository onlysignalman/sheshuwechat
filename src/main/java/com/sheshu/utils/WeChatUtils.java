package com.sheshu.utils;

import com.sheshu.constant.WeChatConfigProperties;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 董帮辉 2017-3-8.
 * 微信的工具类
 */
public class WeChatUtils {

    /**
     * 得到一个32位随机字符
     *
     * @return
     */
    public static String getNoncestr() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(new String(
                "yyyyMMddHHmmss"));
        return formatter.format(now) + buildRandom(2);
    }

    /**
     * 取出一个指定长度大小的随机正整数.
     *
     * @param length
     *            int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * js接口签名校验
     * @param jsapi_ticket
     * @param timestamp
     * @param noncestr
     * @param url
     * @return
     * @throws Exception
     */
    public  static String jssdkSignature(String jsapi_ticket, String timestamp, String noncestr, String url) throws Exception {
        jsapi_ticket = "jsapi_ticket=" + jsapi_ticket;
        noncestr = "noncestr=" + noncestr;
        timestamp = "timestamp=" + timestamp;
        url = "url=" + url;
        String arrys[]=new String[]{jsapi_ticket,noncestr,timestamp,url};
        Arrays.asList(arrys);
        StringBuffer buffer=new StringBuffer();
        for(int i=0;i<arrys.length;i++){
            buffer.append(arrys[i]);
            if(i!=arrys.length-1)
                buffer.append("&");
        }
        return SecurityUtils.SHA1(buffer.toString());
    }

}
