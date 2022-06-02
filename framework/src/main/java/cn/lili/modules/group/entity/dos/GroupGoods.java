package cn.lili.modules.group.entity.dos;


import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 团购商品
 *
 * @author chc
 * @since 2022-5-19 11:15:53
 */
@Data
@ApiModel(value = "团购商品")
@TableName(value = "li_group_goods")
public class GroupGoods extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6010926114971442216L;

    @ApiModelProperty("团购价格")
    private double groupBuyPrice;

    @ApiModelProperty("原价")
    private double originalPrice;

    @ApiModelProperty("商品Id")
    private String goodsId;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("活动库存")
    private Integer activityQuantity;

    @ApiModelProperty("店铺Id")
    private String storeId;

    @ApiModelProperty("店铺名称")
    private String storeName;

    @ApiModelProperty("规格Id")
    private String skuId;

    @ApiModelProperty("规格信息")
    private String specs;

    @ApiModelProperty("缩略图地址")
    private String thumbnail;

}
