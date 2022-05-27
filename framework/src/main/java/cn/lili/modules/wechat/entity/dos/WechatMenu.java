package cn.lili.modules.wechat.entity.dos;

import cn.lili.common.utils.BeanUtil;
import cn.lili.modules.wechat.entity.enums.WxMenuTypeEnums;
import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.mp.bean.menu.WxMpSelfMenuInfo;

/**
 * @author: ftyy
 * @description: 微信公众号菜单
 */
@Data
@TableName("li_wechat_menu")
@ApiModel(value = "微信公众号菜单")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WechatMenu extends BaseEntity {


    @ApiModelProperty(value = "菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型")
    private WxMenuTypeEnums type;

    @ApiModelProperty(value = "菜单标题，不超过16个字节，子菜单不超过60个字节")
    private String name;

    @ApiModelProperty(value = "click等点击类型必须，菜单 KEY 值，用于消息接口推送，不超过128字节")
    private String wxKey;

    @ApiModelProperty(value = "view、miniprogram类型必须，网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为 miniprogram 时，不支持小程序的老版本客户端将打开本url。")
    private String url;

    @ApiModelProperty(value = "media_id类型和view_limited类型必须，调用新增永久素材接口返回的合法media_id")
    private String mediaId;

    @ApiModelProperty(value = "miniprogram类型必须，小程序的appid（仅认证公众号可配置）")
    private String appId;

    @ApiModelProperty(value = "miniprogram类型必须，小程序的页面路径")
    private String pagePath;

    @ApiModelProperty(value = "article_id类型和article_view_limited类型必须，发布后获得的合法 article_id")
    private String articleId;

    @ApiModelProperty(value = "父id")
    private String parentId = "0";

    public WechatMenu(WxMpSelfMenuInfo.WxMpSelfMenuButton wxMpSelfMenuButton){
        BeanUtil.copyProperties(wxMpSelfMenuButton,this);
        this.wxKey=wxMpSelfMenuButton.getKey();
    }

}
