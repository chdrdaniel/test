package cn.lili.modules.wechat.handler;

import cn.lili.common.utils.BeanUtil;
import cn.lili.modules.wechat.builder.TextBuilder;
import cn.lili.modules.wechat.entity.dos.WxSubscribe;
import cn.lili.modules.wechat.entity.enums.SubscribeStatusEnums;
import cn.lili.modules.wechat.service.WxSubscribeService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: ftyy
 * @description: 微信公众号关注事件
 */
@Component
@Slf4j
public class SubscribeHandler implements WxMpMessageHandler {

    @Autowired
    private WxSubscribeService wxSubscribeService;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private TextBuilder textBuilder;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        log.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        // 获取微信用户基本信息
        try {
            WxMpUser userWxInfo = weixinService.getUserService()
                .userInfo(wxMessage.getFromUser(), null);
            if (userWxInfo != null) {

                //添加数据库
                WxSubscribe wxSubscribe = WxSubscribe.builder()
                        .id(userWxInfo.getOpenId())
                        .subscribeStatus(SubscribeStatusEnums.ALREADY_SUBSCRIBE.name()).build();
                //获取用户基本信息(UnionID机制)
                WxMpUser user = wxMpService.getUserService().userInfo(userWxInfo.getOpenId(), "zh_CN");

                if(user!=null){
                    BeanUtil.copyProperties(user,wxSubscribe);
                }
                wxSubscribeService.saveOrUpdate(wxSubscribe);
            }

            return textBuilder.build("感谢关注", wxMessage, weixinService);
        } catch (WxErrorException e) {
            if (e.getError().getErrorCode() == 48001) {
                log.info("该公众号没有获取用户信息权限！");
            }
        }
        return null;
    }
}
