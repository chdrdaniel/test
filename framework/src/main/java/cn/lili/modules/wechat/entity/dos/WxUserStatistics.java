package cn.lili.modules.wechat.entity.dos;

import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: ftyy
 * @description: 微信统计
 */
@Data
@TableName("li_wechat_user_statistics")
@ApiModel(value = "微信统计--新增用户")
@Builder
@EqualsAndHashCode(callSuper = true)
public class WxUserStatistics extends BaseEntity {

    @ApiModelProperty(value = "用户的渠道，数值代表的含义如下： 0代表其他合计 1代表公众号搜索 17代表名片分享 30代表扫描二维码 51代表支付后关注（在支付完成页） 57代表文章内账号名称 100微信广告 161他人转载 200视频号 201直播")
    private Integer userSource;

    @ApiModelProperty(value = "新增的用户数量")
    private Integer newUser;

    @ApiModelProperty(value = "取消关注的用户数量，new_user减去cancel_user即为净增用户数量")
    private Integer cancelUser;

    @ApiModelProperty(value = "总用户量")
    private Integer cumulateUser;
}
