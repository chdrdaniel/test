package cn.lili.modules.system.entity.dto;

import lombok.Data;

/**
 * @author: ftyy
 * @description: 微信公众号设置
 */
@Data
public class WechatPublicSetting {
    /**
     * 微信公众号关注消息
     */
    private String subscribeMessage = "";
    /**
     * 微信公众号取消关注消息
     */
    private String unsubscribeMessage = "";
}
