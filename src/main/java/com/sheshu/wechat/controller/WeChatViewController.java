package com.sheshu.wechat.controller;

import com.sheshu.utils.SecurityUtils;
import com.sheshu.utils.WeChatUtils;
import com.sheshu.wechat.bean.page.JsApiTicket;
import com.sheshu.wechat.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by 董帮辉 on 2017-4-22.
 */
@Controller
@RequestMapping("weChatView")
public class WeChatViewController {

    @Autowired
    private WeChatService weChatService;

    /**
     * 返回html模板.
     */
    @RequestMapping("/helloHtml")
    public String helloHtml(Model model, String code) throws IOException {

        return "/hello";
    }


}
