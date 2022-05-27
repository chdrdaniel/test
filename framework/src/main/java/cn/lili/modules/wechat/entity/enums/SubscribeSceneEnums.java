package cn.lili.modules.wechat.entity.enums;

/**
 * @author: ftyy
 * @description: 微信公众号关注来源
 */
public enum SubscribeSceneEnums {
    /**
     * 公众号搜索
     */
    ADD_SCENE_SEARCH("公众号搜索"),

    /**
     * 公众号迁移
     */
    ADD_SCENE_ACCOUNT_MIGRATION("公众号迁移"),

    /**
     * 名片分享
     */
    ADD_SCENE_PROFILE_CARD("名片分享"),

    /**
     * 扫描二维码
     */
    ADD_SCENE_QR_CODE("扫描二维码"),

    /**
     * 图文页内名称点击
     */
    ADD_SCENE_PROFILE_LINK("图文页内名称点击"),

    /**
     * 图文页右上角菜单
     */
    ADD_SCENE_PROFILE_ITEM("图文页右上角菜单"),

    /**
     * 支付后关注
     */
    ADD_SCENE_PAID("支付后关注"),

    /**
     * 微信广告
     */
    ADD_SCENE_WECHAT_ADVERTISEMENT("微信广告"),

    /**
     * 他人转载
     */
    ADD_SCENE_REPRINT("他人转载"),

    /**
     * 视频号直播
     */
    ADD_SCENE_LIVESTREAM("视频号直播"),

    /**
     * 视频号
     */
    ADD_SCENE_CHANNELS("视频号"),

    /**
     * 其他
     */
    ADD_SCENE_OTHERS("其他");


    private String description;

    public String description() {
        return description;
    }

    SubscribeSceneEnums(String description) {
        this.description = description;
    }

}
