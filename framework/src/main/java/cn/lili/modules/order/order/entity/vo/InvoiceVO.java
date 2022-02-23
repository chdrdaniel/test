package cn.lili.modules.order.order.entity.vo;

import cn.lili.modules.goods.entity.dos.Goods;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: ftyy
 * @date: 2022-02-23 17:08
 * @description: 发货单
 */
@Data
public class InvoiceVO {

    @ApiModelProperty(value = "交易编号")
    private String orderSn;

    @ApiModelProperty(value = "订单时间")
    private Date orderTime;

    @ApiModelProperty(value = "打印时间")
    private Date printTime;

    @ApiModelProperty(value = "收件人")
    private String  userName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "地址")
    private String region;

    @ApiModelProperty(value = "买家留言")
    private String  leavingMessage;

    @ApiModelProperty(value = "商品列表")
    private List<Goods> goodsList;

    @ApiModelProperty(value = "店铺名称")
    private String storeName;

    @ApiModelProperty(value = "联系人")
    private String salesConsigneeName;

    @ApiModelProperty(value = "店铺电话")
    private String salesConsigneeMobile;

    @ApiModelProperty(value = "地址名称， '，'分割")
    private String salesConsigneeAddressPath;

    @ApiModelProperty(value = "详细地址")
    private String salesConsigneeDetail;

    @ApiModelProperty(value = "卖家备注")
    private String storeRemarks;


}
