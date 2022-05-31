package cn.lili.modules.wechat.entity.dos;

import cn.lili.common.utils.BeanUtil;
import cn.lili.modules.wechat.entity.dto.WxSendTemplateParams;
import cn.lili.modules.wechat.entity.enums.WxMessageTypeEnums;
import cn.lili.modules.wechat.entity.enums.WxNewsTypeEnums;
import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

import java.util.Date;

/**
 * @author: ftyy
 * @description: 微信公众号消息
 */
@Data
@TableName("li_wechat_the_public_message")
@ApiModel(value = "微信公众号消息")
@NoArgsConstructor
public class WechatThePublicMessage extends BaseEntity {

    @ApiModelProperty(value = "开发者微信号")
    private String toUserName;

    @ApiModelProperty(value = "发送方帐号（一个OpenID）")
    private String fromUserName;

    @ApiModelProperty(value = "消息创建时间")
    private Long wechatCreateTime;
    /**
     * @see WxNewsTypeEnums
     */
    @ApiModelProperty(value = "消息类型")
    private String msgType;

    @ApiModelProperty(value = "文本消息内容")
    private String content;

    @ApiModelProperty(value = "图片链接（由系统生成）")
    private String picUrl;

    @ApiModelProperty(value = "图片消息媒体id/视频消息媒体id/图片消息媒体id，可以调用获取临时素材接口拉取数据--小视频类型/视频/语音/图片类型")
    private String mediaId;

    @ApiModelProperty(value = "语音格式，如amr，speex等--语音类型")
    private String format;

    @ApiModelProperty(value = "语音识别结果，UTF8编码--语音类型")
    private String recognition;

    @ApiModelProperty(value = "视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据--小视频/视频类型")
    private String thumbMediaId;

    @ApiModelProperty(value = "地理位置纬度--地理位置类型")
    private String locationX;

    @ApiModelProperty(value = "地理位置经度--地理位置类型")
    private String locationY;

    @ApiModelProperty(value = "地图缩放大小--地理位置类型")
    private String scale;

    @ApiModelProperty(value = "地理位置信息--地理位置类型")
    private String label;

    @ApiModelProperty(value = "消息标题--链接类型")
    private String title;

    @ApiModelProperty(value = "消息描述--链接类型")
    private String description;

    @ApiModelProperty(value = "消息链接--链接类型")
    private String url;

    @ApiModelProperty(value = "消息id，64位整型")
    private String msgId;

    @ApiModelProperty(value = "消息的数据ID（消息如果来自文章时才有）")
    private String msgDataId;

    @ApiModelProperty(value = "多图文时第几篇文章，从1开始（消息如果来自文章时才有）")
    private String idx;

    /**
     * @see WxMessageTypeEnums
     */
    @ApiModelProperty(value = "消息类型-接收(receive) 回复(reply)")
    private String messageType;

    @ApiModelProperty(value = "微信模板消息id")
    private String wxTemplateId;

    public WechatThePublicMessage(WxMpXmlMessage wxMpXmlMessage){
        BeanUtil.copyProperties(wxMpXmlMessage,this);
        this.toUserName=wxMpXmlMessage.getToUser();
        this.fromUserName=wxMpXmlMessage.getFromUser();
        this.setWechatCreateTime(wxMpXmlMessage.getCreateTime());
        this.messageType=WxMessageTypeEnums.WX_MESSAGE_RECEIVE.name();
    }

    public WechatThePublicMessage(WxSendTemplateParams wxSendTemplateParams){
        this.fromUserName=wxSendTemplateParams.getOpenId();
        this.setWechatCreateTime(System.currentTimeMillis());
        this.messageType=WxMessageTypeEnums.WX_MESSAGE_REPLY.name();
    }

}
