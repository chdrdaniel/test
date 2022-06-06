package cn.lili.modules.wechat.entity.enums;

/**
 * @author: ftyy
 * @description: 素材类型枚举
 */
public enum MaterialUploadEnums {

    /**
     * 图片（image）
     */
    WX_MATERIAL_UPLOAD_IMAGE("image"),
    /**
     * 视频（video）
     */
    WX_MATERIAL_UPLOAD_VIDEO("video"),
    /**
     * 语音 （voice）
     */
    WX_MATERIAL_UPLOAD_VOICE("voice"),
    /**
     * 图文（news）
     */
    WX_MATERIAL_UPLOAD_NEWS("news");


    private String description;

    public String description() {
        return description;
    }

    MaterialUploadEnums(String description) {
        this.description = description;
    }
}
