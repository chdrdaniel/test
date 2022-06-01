package cn.lili.modules.wechat.entity.dos;

import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author: ftyy
 * @description: 描述
 */
@Data
@TableName("li_wechat_interface_statistics")
@ApiModel(value = "微信统计--接口统计")
public class WxInterfaceStatistics extends BaseEntity {

    @ApiModelProperty(value = "数据的小时")
    private Integer refHour=0;

    @ApiModelProperty(value = "通过服务器配置地址获得消息后，被动回复用户消息的次数")
    private Integer callbackCount=0;

    @ApiModelProperty(value = "上述动作的失败次数")
    private Integer failCount=0;

    @ApiModelProperty(value = "总耗时，除以callback_count即为平均耗时")
    private Integer totalTimeCost=0;

    @ApiModelProperty(value = "最大耗时")
    private Integer maxTimeCost=0;

}
