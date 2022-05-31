package cn.lili.modules.wechat.entity.dto;

import cn.lili.modules.wechat.entity.enums.WxNewsTypeEnums;
import lombok.Data;

import java.util.Map;

/**
 * @author: ftyy
 * @description: 发送微信公众号模板消息类
 */
@Data
public class WxSendTemplateParams {

    /**
     * 微信消息模板id
     */
    private String templateId;

    /**
     * 内容
     */
    private Map<String,String> content;

    /**
     * 发送用户openId
     */
    private String openId;

    /**
     * @see WxNewsTypeEnums
     * 模板类型
     */
    private String templateType;
}
