package cn.lili.modules.wechat.entity.dos;

import cn.lili.mybatis.BaseEntity;
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
    private String openId;

    @ApiModelProperty(value = "关注者状态")
    private String subscribeStatus;

}
