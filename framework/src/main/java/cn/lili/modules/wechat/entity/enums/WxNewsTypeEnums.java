package cn.lili.modules.wechat.entity.enums;

/**
 * @author: ftyy
 * @description:微信消息类型枚举
 */
public enum WxNewsTypeEnums {

    /**
     * 消息类型，文本为text
     */
    WX_NEWS_TYPE("text"),
    /**
     * 消息类型，图片为image
     */
    WX_NEWS_IMAGE("image"),
    /**
     * 语音为voice
     */
    WX_NEWS_VOICE("voice"),
    /**
     * 视频为video
     */
    WX_NEWS_VIDEO("video"),
    /**
     * 小视频为shortvideo
     */
    WX_NEWS_SHORT_VIDEO("shortvideo"),
    /**
     * 地理位置为location
     */
    WX_NEWS_LOCATION("location"),
    /**
     * 链接为link
     */
    WX_NEWS_LINK("link");


    private String description;

    public String description() {
        return description;
    }

    WxNewsTypeEnums(String description) {
        this.description = description;
    }

}
