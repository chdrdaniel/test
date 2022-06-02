package cn.lili.modules.wechat.entity.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author: ftyy
 * @description: 微信公众号关注
 */
@Data
@TableName("li_wechat_subscribe")
@ApiModel(value = "微信公众号关注")
@Builder
public class WxSubscribe {

    @ApiModelProperty(value = "关注者openId")
    private String id;

    @ApiModelProperty(value = "关注者状态")
    private String subscribeStatus;

    @ApiModelProperty(value = "公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注")
    private String remark;

    @ApiModelProperty(value = "用户所在的分组ID（兼容旧的用户分组接口）")
    private String groupId;

    @ApiModelProperty(value = "用户被打上的标签 ID 列表")
    private String tagid_list;

    @ApiModelProperty(value = "用户被打上的标签名称列表")
    private String tagIdNameList;

    @ApiModelProperty(value = "返回用户关注的渠道来源")
    private String subscribe_scene;

    @ApiModelProperty(value = "用户头像")
    private String headImgUrl;




}
