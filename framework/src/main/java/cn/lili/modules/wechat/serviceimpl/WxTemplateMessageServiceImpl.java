package cn.lili.modules.wechat.serviceimpl;

import cn.lili.common.enums.ResultCode;
import cn.lili.common.exception.ServiceException;
import cn.lili.modules.wechat.entity.dos.WechatThePublicMessage;
import cn.lili.modules.wechat.entity.dos.WxTemplateMessage;
import cn.lili.modules.wechat.entity.dto.WxSendTemplateParams;
import cn.lili.modules.wechat.entity.enums.WxNewsTypeEnums;
import cn.lili.modules.wechat.mapper.WxTemplateMessageMapper;
import cn.lili.modules.wechat.service.WechatThePublicMessageService;
import cn.lili.modules.wechat.service.WxTemplateMessageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

/**
 * @author: ftyy
 * @description: 微信公众号模板消息业务实现层
 */
@Service
public class WxTemplateMessageServiceImpl  extends ServiceImpl<WxTemplateMessageMapper, WxTemplateMessage> implements WxTemplateMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatThePublicMessageService wechatThePublicMessageService;

    @Autowired
    private WxTemplateMessageService wxTemplateMessageService;

    @Override
    public void sendTemplateMessage(WxSendTemplateParams wxSendTemplateParams){

        WxTemplateMessage wxTemplateMessage = this.getOne(new LambdaQueryWrapper<WxTemplateMessage>().eq(WxTemplateMessage::getWxTemplateId,wxSendTemplateParams.getTemplateId()));
        //校验微信模板消息为空或者发送内容为空
        if(wxTemplateMessage==null || wxSendTemplateParams.getContent().isEmpty()){
            throw new ServiceException(ResultCode.ERROR);
        }
        try {
            // 创建模板消息，设置模板id、指定模板消息要发送的目标用户
            WxMpTemplateMessage wxMpTemplateMessage = WxMpTemplateMessage.builder()
                    .templateId(wxSendTemplateParams.getTemplateId())
                    .toUser(wxSendTemplateParams.getOpenId())
                    .build();

            wxSendTemplateParams.getContent().forEach((k,v)->{
                // 填充模板消息中的变量
                wxMpTemplateMessage.addData(new WxMpTemplateData(k,v));
            });
            // 发送模板消息，返回消息id
            String templateId = wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
            //添加发送模板消息
            saveWechatThePublicMessage(wxSendTemplateParams,templateId,wxTemplateMessage);

        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }


    public void saveWechatThePublicMessage(WxSendTemplateParams wxSendTemplateParams,String templateId,WxTemplateMessage wxTemplateMessage){
        //获取发送消息类型
        WxNewsTypeEnums wxNewsTypeEnums = WxNewsTypeEnums.valueOf(wxSendTemplateParams.getTemplateType());
        //创建发送消息类
        WechatThePublicMessage wechatThePublicMessage = new WechatThePublicMessage(wxSendTemplateParams);
        wechatThePublicMessage.setWxTemplateId(templateId);
        switch (wxNewsTypeEnums){
            //消息类型为文本
            case WX_NEWS_TYPE:
                //微信模板消息内容
                String wxTemplateContent = wxTemplateMessage.getWxTemplateContent();
                Iterator<Map.Entry<String, String>> iterator =  wxSendTemplateParams.getContent().entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    //填充模板发送消息内容
                    wxTemplateContent=wxTemplateContent.replace("{{"+entry.getKey()+"}}",entry.getValue());
                }
                wechatThePublicMessage.setContent(wxTemplateContent);
                break;
            case WX_NEWS_IMAGE:

            case WX_NEWS_VOICE:

            case WX_NEWS_VIDEO:

            case WX_NEWS_SHORT_VIDEO:

            case WX_NEWS_LOCATION:

            case WX_NEWS_LINK:

            default:{
                throw new ServiceException(ResultCode.ERROR);
            }
        }
        //新增消息内容
        wechatThePublicMessageService.save(wechatThePublicMessage);

    }

}
