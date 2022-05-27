package cn.lili.modules.wechat.handler;

import cn.lili.modules.wechat.builder.TextBuilder;
import cn.lili.modules.wechat.entity.dos.WxSubscribe;
import cn.lili.modules.wechat.entity.enums.SubscribeStatusEnums;
import cn.lili.modules.wechat.service.WxSubscribeService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author: ftyy
 * @description: 微信公众号取消关注事件
 */
@Component
@Slf4j
public class UnsubscribeHandler implements WxMpMessageHandler {

    @Autowired
    private WxSubscribeService wxSubscribeService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        String openId = wxMessage.getFromUser();
        log.info("取消关注用户 OPENID: " + openId);
        //更新数据库为取消关注
        wxSubscribeService.update(new LambdaUpdateWrapper<WxSubscribe>().eq(WxSubscribe::getOpenId,openId).set(WxSubscribe::getSubscribeStatus,SubscribeStatusEnums.CANCEL_SUBSCRIBE.name()));

        return new TextBuilder().build("感谢陪伴", wxMessage, wxMpService);
    }

}
