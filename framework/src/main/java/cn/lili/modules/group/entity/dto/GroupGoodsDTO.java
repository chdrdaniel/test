package cn.lili.modules.group.entity.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 团购商品DTO
 *
 * @Author chc
 * @since 2022/5/25 15:55
 */
public class GroupGoodsDTO {

    @ApiModelProperty("团购价格")
    private double groupBuyPrice;

    @ApiModelProperty("活动库存")
    private Integer activityQuantity;
}
