package cn.lili.modules.wechat.entity.dos;

import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: ftyy
 * @description: 微信用户标签
 */
@Data
@TableName("li_wechat_user_label")
@ApiModel(value = "微信用户标签")
@EqualsAndHashCode(callSuper = true)
public class WxUserLabel extends BaseEntity {

    @ApiModelProperty(value = "标签名称")
    private String  name;

    @ApiModelProperty(value = "标签粉丝数")
    private Integer count;

}
