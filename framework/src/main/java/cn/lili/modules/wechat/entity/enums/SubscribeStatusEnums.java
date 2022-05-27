package cn.lili.modules.wechat.entity.enums;

/**
 * @author: ftyy
 * @description: 微信公众号关注状态
 */
public enum SubscribeStatusEnums {
    /**
     * 已关注
     */
    ALREADY_SUBSCRIBE("已关注"),
    /**
     * 取消关注
     */
    CANCEL_SUBSCRIBE("取消关注");


    private String description;

    public String description() {
        return description;
    }

    SubscribeStatusEnums(String description) {
        this.description = description;
    }
}
