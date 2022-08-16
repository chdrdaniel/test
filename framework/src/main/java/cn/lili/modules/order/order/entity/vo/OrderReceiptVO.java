package cn.lili.modules.order.order.entity.vo;

import cn.lili.modules.order.order.entity.dos.Order;
import cn.lili.modules.order.order.entity.dos.Receipt;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 发票
 *
 * @author Bulbasaur
 * @since 2020/11/28 11:38
 */
@Data
@ApiModel(value = "发票")
public class OrderReceiptVO implements Serializable {

    @ApiModelProperty(value = "订单")
    private Order order;

    @ApiModelProperty(value = "发票")
    private Receipt receipt;
}
