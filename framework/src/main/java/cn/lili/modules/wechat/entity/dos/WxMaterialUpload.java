package cn.lili.modules.wechat.entity.dos;

import cn.lili.modules.wechat.entity.enums.MaterialUploadEnums;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ftyy
 * @description: 描述
 */
@Data
@TableName("li_wechat_material_upload")
@ApiModel(value = "微信公众号素材")
public class WxMaterialUpload{

    @ApiModelProperty(value = "微信公众号素材id")
    private String media_id;

    @ApiModelProperty(value = "图文消息的标题")
    private String title;

    @ApiModelProperty(value = "图文消息的封面图片素材id（必须是永久mediaID）")
    private String thumb_media_id;

    @ApiModelProperty(value = "是否显示封面，0为false，即不显示，1为true，即显示")
    private String show_cover_pic;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空")
    private String digest;

    @ApiModelProperty(value = "图文消息的具体内容，支持 HTML 标签，必须少于2万字符，小于1M，且此处会去除JS")
    private String content;

    @ApiModelProperty(value = "图文页的URL，或者，当获取的列表是图片素材列表时，该字段是图片的URL")
    private String url;

    @ApiModelProperty(value = "图文消息的原文地址，即点击“阅读原文”后的URL")
    private String content_source_url;

    @ApiModelProperty(value = "这篇图文消息素材的最后更新时间")
    private String update_time;

    @ApiModelProperty(value = "文件名称")
    private String name;

    /**
     * @see MaterialUploadEnums
     */
    @ApiModelProperty(value = "素材类型")
    private String materialUploadType;
}
