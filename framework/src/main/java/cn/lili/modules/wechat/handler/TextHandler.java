package cn.lili.modules.wechat.handler;

import cn.lili.modules.wechat.entity.dos.WechatThePublicMessage;
import cn.lili.modules.wechat.entity.dos.WechatThePublicReplyMessage;
import cn.lili.modules.wechat.entity.enums.WxNewsTypeEnums;
import cn.lili.modules.wechat.service.WechatThePublicMessageService;
import cn.lili.modules.wechat.service.WechatThePublicReplyMessageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author: ftyy
 * @description: 微信文本消息处理器
 */
@Component
public class TextHandler implements WxMpMessageHandler {

    /**
     * 微信公众消息
     */
    @Autowired
    private WechatThePublicMessageService wechatThePublicMessageService;

    /**
     * 微信公众消息回复
     */
    @Autowired
    private WechatThePublicReplyMessageService wechatThePublicReplyMessageService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        //记录微信公众号消息
        WechatThePublicMessage wechatThePublicMessage = new WechatThePublicMessage(wxMpXmlMessage);
        wechatThePublicMessageService.saveOrUpdate(wechatThePublicMessage);
        //接受消息内容
        String content = wxMpXmlMessage.getContent();
        //匹配回复消息内容
        List<WechatThePublicReplyMessage> list = wechatThePublicReplyMessageService.list(new LambdaQueryWrapper<WechatThePublicReplyMessage>()
                .like(WechatThePublicReplyMessage::getReplyTitle,content).eq(WechatThePublicReplyMessage::getReplyType, WxNewsTypeEnums.WX_NEWS_TYPE.description()));
        //响应内容消息
        String outContent="亲,您的表述能在详细一点吗。";
        //匹配不同关键字返回响应内容
        if(!list.isEmpty() && list.size()>0){
            outContent= list.get(0).getReplyContent();
        }
        return WxMpXmlOutMessage.TEXT().content(outContent).fromUser(wxMpXmlMessage.getToUser()).toUser(wxMpXmlMessage.getFromUser()).build();
    }
}
