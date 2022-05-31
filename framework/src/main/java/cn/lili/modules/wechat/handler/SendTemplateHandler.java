package cn.lili.modules.wechat.handler;

import cn.lili.modules.wechat.entity.dto.WxSendTemplateParams;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: ftyy
 * @description: 微信公众号消息模板发送类
 */
@Component
public class SendTemplateHandler {

    @Autowired
    private WxMpService wxMpService;

    public String sendTemplateMessage(WxSendTemplateParams wxSendTemplateParams){
        try {
            // 创建模板消息，设置模板id、指定模板消息要发送的目标用户
            WxMpTemplateMessage wxMpTemplateMessage = WxMpTemplateMessage.builder()
                    .templateId("ROSoYWeo_dFO4q2DFk4d7XzCMq-U7uYxDP9UJszQH-g")
                    .toUser("oUvmc6WeodkutBDGFKSbhw7DYc3k")
                    .build();
            // 填充模板消息中的变量
            wxMpTemplateMessage.addData(new WxMpTemplateData("text", "华为mate40pro"));
            // 发送模板消息，返回消息id
            return wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return null;
    }



}
