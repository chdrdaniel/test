package cn.lili.modules.wechat.config;

import cn.lili.modules.wechat.entity.enums.WxNewsTypeEnums;
import cn.lili.modules.wechat.handler.SubscribeHandler;
import cn.lili.modules.wechat.handler.TextHandler;
import cn.lili.modules.wechat.handler.UnsubscribeHandler;
import com.binarywang.spring.starter.wxjava.mp.properties.WxMpProperties;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static me.chanjar.weixin.common.api.WxConsts.EventType.SUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.EventType.UNSUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType.EVENT;

/**
 * @author: ftyy
 * @description: 微信公众号消息路由规则配置类
 */
@Configuration
@EnableConfigurationProperties(WxMpProperties.class)
public class WxJavaConfig {

    /**
     * 微信公众号文字消息
     */
    @Autowired
    private TextHandler textHandler;

    /**
     * 关注事件
     */
    @Autowired
    private SubscribeHandler subscribeHandler;

    /**
     * 取消关注事件
     */
    @Autowired
    private UnsubscribeHandler unsubscribeHandler;

    @Autowired
    private WxMpService wxMpService;


    @Bean
    public WxMpMessageRouter messageRouter() {
        //创建消息路由
        final WxMpMessageRouter wxMpMessageRouter = new WxMpMessageRouter(wxMpService);

        //创建为本消息路由
        wxMpMessageRouter.rule().async(false).msgType(WxNewsTypeEnums.WX_NEWS_TYPE.description()).handler(textHandler).end();
        // 关注事件
        wxMpMessageRouter.rule().async(false).msgType(EVENT).event(SUBSCRIBE).handler(this.subscribeHandler).end();
        // 取消关注事件
        wxMpMessageRouter.rule().async(false).msgType(EVENT).event(UNSUBSCRIBE).handler(this.unsubscribeHandler).end();

        return wxMpMessageRouter;
    }


}
