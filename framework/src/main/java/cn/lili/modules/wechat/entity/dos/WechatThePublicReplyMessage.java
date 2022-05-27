package cn.lili.modules.wechat.entity.dos;

import cn.lili.modules.wechat.entity.enums.WxNewsTypeEnums;
import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ftyy
 * @description: 微信公众号消息回复
 */
@Data
@TableName("li_wechat_the_public_reply_message")
@ApiModel(value = "微信公众号消息回复")
public class WechatThePublicReplyMessage extends BaseEntity {

    @ApiModelProperty(value = "回复标题")
    private String replyTitle;

    @ApiModelProperty(value = "回复内容")
    private String replyContent;

    /**
     * @see WxNewsTypeEnums
     */
    @ApiModelProperty(value = "回复类型")
    private String replyType;
}
