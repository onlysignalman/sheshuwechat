package com.sheshu.wechat.controller;

import com.sheshu.constant.WeChatConfigProperties;
import com.sheshu.utils.DataShapeConvertUtils;
import com.sheshu.utils.WeChatUtils;
import com.sheshu.wechat.bean.base.TicketSign;
import com.sheshu.wechat.bean.base.WeChat;
import com.sheshu.wechat.bean.user.UserInfo;
import com.sheshu.wechat.bean.user.UserListInfo;
import com.sheshu.wechat.service.MessageService;
import com.sheshu.wechat.service.UserService;
import com.sheshu.wechat.service.ValidateService;
import com.sheshu.wechat.service.WeChatService;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Date;

/**
 * 董帮辉 2017-3-5.
 */
@RestController
@RequestMapping("/wechat")
public class WechatController {

    //注入微信校验的service
    @Autowired
    private ValidateService validateService;

    //微信消息回复service
    @Autowired
    private MessageService messageService;

    //用户管理service
    @Autowired
    private UserService userService;

    @Autowired
    private WeChatService weChatService;

    //配置属性service
    @Autowired
    private WeChatConfigProperties weChatConfigProperties;

    /**
     * 校验微信服务器信息
     * @param weChat
     * @return
     */
    @RequestMapping(value = "/checkSignature",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public String checkSignature(WeChat weChat){
        //对微信的信息进行校验
        String result = this.validateService.checkSignature(weChat);
        return result;
    }

    /**
     * 当普通微信用户向公众账号发消息时，微信服务器将POST消息的XML数据包到开发者填写的URL上。
     * @return
     */
    @RequestMapping(value = "/checkSignature",method = RequestMethod.POST, produces = "application/xml;charset=UTF-8")
    public String responseMessage(HttpServletRequest request) throws IOException, DocumentException {
        Map<String, String> map = DataShapeConvertUtils.parseXml(request);
        String result = this.messageService.responseMessage(map);
        return result;
    }

    /**
     * 微信JS接口验证入口分享签名
     * 把生成的jsapi_ticket保存到缓存或者单列的Bean属性中
     *  调用js接口的凭证jsapi_ticket 如果存在 判断是否超时 有效时间为 7200秒
     *  如果超时重新获取
     * @return
     */
    @RequestMapping(value = "getJssdkSignature", method = RequestMethod.POST)
    public TicketSign getJssdkSignature(String url) throws Exception {
        TicketSign ticketSign = new TicketSign();
        ticketSign.setUrl(url);
        ticketSign.setTimestamp(new Date().getTime()+"");
        ticketSign.setNoncestr(WeChatUtils.getNoncestr());
        ticketSign.setAppId(weChatConfigProperties.getAppId());
        String signature = WeChatUtils.jssdkSignature(weChatService.getJsApiTiket().getTicket(), ticketSign.getTimestamp(), ticketSign.getNoncestr(), ticketSign.getUrl());
        ticketSign.setSignature(signature);
        return ticketSign;
    }

    /**
     * 获取关注的用户信息列表
     * @return
     */
    @RequestMapping(value = "getUserList", method = RequestMethod.GET)
    public UserListInfo getUserList() throws IOException {
        UserListInfo userListInfo =  this.userService.getUserListInfo();
        return userListInfo;
    }

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping(value = "getUserInfo",method = RequestMethod.GET)
    public UserInfo getUserInfo(@RequestParam("openid")String openid) throws IOException {
        UserInfo userInfo = this.userService.getUserInfo(openid);
        return userInfo;
    }

}
