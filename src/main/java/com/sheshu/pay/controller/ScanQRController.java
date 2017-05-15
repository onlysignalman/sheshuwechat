package com.sheshu.pay.controller;

import com.sheshu.pay.service.ScanQRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dbh on 2017/5/2.
 * 扫码支付
 */
@RequestMapping("scanQR")
@Controller
public class ScanQRController {

    @Autowired
    private ScanQRService scanQRService;

    @RequestMapping(value = "unifiedorder", method = RequestMethod.GET)
    public String unifiedorder(HttpServletRequest request){
        String addr = request.getRemoteAddr();
        this.scanQRService.unifiedorder(addr);
        return null;
    }

}
