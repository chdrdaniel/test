package cn.lili.modules.wechat.entity.dos;

import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ftyy
 * @description: 微信公众号模板消息
 */
@Data
@TableName("li_wechat_template_message")
@ApiModel(value = "微信公众号模板消息")
public class WxTemplateMessage  extends BaseEntity {


    @ApiModelProperty(value = "微信消息模板Id")
    private String wxTemplateId;

    @ApiModelProperty(value = "微信消息模板标题")
    private String wxTemplateTitle;

    @ApiModelProperty(value = "微信消息模板标题")
    private String wxTemplateContent;

}
