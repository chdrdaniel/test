package cn.lili.modules.wechat.config;

import cn.lili.modules.wechat.entity.enums.WxNewsTypeEnums;
import cn.lili.modules.wechat.handler.TextHandler;
import com.binarywang.spring.starter.wxjava.mp.properties.WxMpProperties;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ftyy
 * @description: 微信公众号消息路由规则配置类
 */
@Configuration
@EnableConfigurationProperties(WxMpProperties.class)
public class WxJavaConfig {

    @Autowired
    private TextHandler textHandler;

    @Autowired
    private WxMpService wxMpService;


    @Bean
    public WxMpMessageRouter messageRouter() {
        //创建消息路由
        final WxMpMessageRouter wxMpMessageRouter = new WxMpMessageRouter(wxMpService);

        //创建为本消息路由
        wxMpMessageRouter.rule().async(false).msgType(WxNewsTypeEnums.WX_NEWS_TYPE.description()).handler(textHandler).end();

        return wxMpMessageRouter;
    }


}
