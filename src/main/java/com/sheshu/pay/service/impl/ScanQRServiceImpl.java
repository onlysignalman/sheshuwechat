package com.sheshu.pay.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sheshu.constant.WeChatConfigProperties;
import com.sheshu.pay.service.ScanQRService;
import com.sheshu.utils.UrlRequestUtils;
import com.sheshu.utils.WeChatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dbh on 2017/5/2.
 */
@Service("scanQRService")
public class ScanQRServiceImpl implements ScanQRService {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private WeChatConfigProperties weChatConfigProperties;

    @Override
    public String unifiedorder(String addr) {

        String unifiedorder = weChatConfigProperties.getUnifiedorder();

        String str = WeChatUtils.getNoncestr();
        Map<String, Object> params = new HashMap<>();
        params.put("appid",weChatConfigProperties.getAppId());
        params.put("mch_id",weChatConfigProperties.getMchId());
        params.put("nonce_str",str);
        params.put("body","暖气费");
        params.put("out_trade_no",str);
        params.put("total_fee",0.01);
        params.put("spbill_create_ip",addr);
        params.put("notify_url","http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php");
        params.put("trade_type","NATIVE");
//        String sign = WeChatUtils.getSign(params);

        String xml = "<xml>\n" +
                "    <appid>"+weChatConfigProperties.getAppId()+"</appid>\n" +
                "    <attach>支付测试</attach>\n" +
                "    <body>暖气费</body>\n" +
                "    <mch_id>"+weChatConfigProperties.getMchId()+"</mch_id>\n" +
                "    <nonce_str>"+str+"</nonce_str>\n" +
                "    <notify_url>http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php</notify_url>\n" +
                "    <out_trade_no>"+str+"</out_trade_no>\n" +
                "    <spbill_create_ip>127.0.0.1</spbill_create_ip>\n" +
                "    <total_fee>0.01</total_fee>\n" +
                "    <trade_type>NATIVE</trade_type>\n" +
                "    <sign>"+"</sign>\n" +
                " </xml> ";

        String unifiedorderUrl = weChatConfigProperties.getUnifiedorder();
        String result = UrlRequestUtils.sendPost(unifiedorderUrl, xml);

        return result;
    }
}
