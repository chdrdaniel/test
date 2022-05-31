package cn.lili.modules.wechat.entity.enums;

/**
 * @author: ftyy
 * @description: 微信消息接收类型
 */

public enum WxMessageTypeEnums {


    /**
     * 消息接收
     */
    WX_MESSAGE_RECEIVE("消息接收"),

    /*
     * 消息回复
     */
    WX_MESSAGE_REPLY("消息回复");


    private String description;

    public String description() {
        return description;
    }

    WxMessageTypeEnums(String description) {
        this.description = description;
    }
}
