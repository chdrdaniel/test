package cn.lili.modules.order.order.entity.vo;

import cn.hutool.json.JSONUtil;
import cn.lili.common.utils.BeanUtil;
import cn.lili.common.utils.StringUtils;
import cn.lili.modules.order.cart.entity.enums.DeliveryMethodEnum;
import cn.lili.modules.order.order.entity.dos.Order;
import cn.lili.modules.order.order.entity.dos.Receipt;
import cn.lili.modules.order.order.entity.enums.DeliverStatusEnum;
import cn.lili.modules.order.order.entity.enums.OrderStatusEnum;
import cn.lili.modules.order.order.entity.enums.PayStatusEnum;
import cn.lili.modules.payment.entity.enums.PaymentMethodEnum;
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

    /**
     * 订单
     */
    private Order order;

    /**
     * 订单状态
     */
    private String orderStatusValue;

    /**
     * 物流类型
     */
    private String deliveryMethodValue;

    /**
     * 发票
     */
    private ReceiptDetailVO receipt;

    @ApiModelProperty(value = "价格详情")
    private String priceDetail;

    public OrderReceiptVO(Order order, Receipt receipt) {
        this.order = order;
        ReceiptDetailVO receiptDetailVO = new ReceiptDetailVO();
        BeanUtil.copyProperties(receipt,receiptDetailVO);
        if(StringUtils.isNotEmpty(receipt.getReceiptFile())){
            receiptDetailVO.setReceiptFiles(JSONUtil.toList(receipt.getReceiptFile(),String.class));
        }
        this.receipt = receiptDetailVO;
    }

    /**
     * 可操作类型
     */
    public AllowOperation getAllowOperationVO() {
        return new AllowOperation(this.order);
    }

    public String getOrderStatusValue() {
        try {
            return OrderStatusEnum.valueOf(order.getOrderStatus()).description();
        } catch (Exception e) {
            return "";
        }
    }

    public String getPayStatusValue() {
        try {
            return PayStatusEnum.valueOf(order.getPayStatus()).description();
        } catch (Exception e) {
            return "";
        }

    }

    public String getDeliverStatusValue() {
        try {
            return DeliverStatusEnum.valueOf(order.getDeliverStatus()).getDescription();
        } catch (Exception e) {
            return "";
        }
    }

    public String getDeliveryMethodValue() {
        try {
            return DeliveryMethodEnum.valueOf(order.getDeliveryMethod()).getDescription();
        } catch (Exception e) {
            return "";
        }
    }

    public String getPaymentMethodValue() {
        try {
            return PaymentMethodEnum.valueOf(order.getPaymentMethod()).paymentName();
        } catch (Exception e) {
            return "";
        }
    }

}
