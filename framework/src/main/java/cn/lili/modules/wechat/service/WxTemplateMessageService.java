package cn.lili.modules.wechat.service;

import cn.lili.modules.wechat.entity.dos.WxTemplateMessage;
import cn.lili.modules.wechat.entity.dto.WxSendTemplateParams;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author: ftyy
 * @description: 微信公众号消息模板业务层
 */
public interface WxTemplateMessageService  extends IService<WxTemplateMessage> {

    /**
     * 微信公众号模板消息发送
     * @param wxSendTemplateParams
     */
    void sendTemplateMessage(WxSendTemplateParams wxSendTemplateParams);
}
