package cn.lili.modules.order.order.entity.dos;

import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


/**
 * 发票
 *
 * @author Bulbasaur
 * @since 2020/11/28 11:38
 */
@Data
@TableName("li_receipt")
@ApiModel(value = "发票")
public class Receipt extends BaseEntity {

    private static final long serialVersionUID = -8210927482915675995L;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "发票抬头")
    private String receiptTitle;

    @ApiModelProperty(value = "公司名称  如果是个人则是个人名称")
    private String companyName;

    @ApiModelProperty(value = "纳税人识别号")
    private String taxpayerId;

    @ApiModelProperty(value = "发票内容")
    private String receiptContent;

    @ApiModelProperty(value = "发票金额")
    private Double receiptPrice;

    @ApiModelProperty(value = "会员ID")
    private String memberId;

    @ApiModelProperty(value = "会员名称")
    private String memberName;

    @ApiModelProperty(value = "商家ID")
    private String storeId;

    @ApiModelProperty(value = "商家名称")
    private String storeName;

    /**
     * @see cn.lili.modules.store.entity.enums.ReceiptSourceEnum
     */
    @ApiModelProperty(value = "发票开具方")
    private String receiptSource;

    @ApiModelProperty(value = "发票状态 0未开 1已开")
    private Integer receiptStatus;

    @ApiModelProperty(value = "发票详情")
    private String receiptDetail;

    /**
     * @see cn.lili.modules.order.order.entity.enums.ReceiptTypeEnum
     */
    @ApiModelProperty(value = "发票类型")
    private String receiptType;

    @ApiModelProperty(value = "注册地址")
    private String registerAddress;

    @ApiModelProperty(value = "注册电话")
    private String registerMobile;

    @ApiModelProperty(value = "开户银行")
    private String bankName;

    @ApiModelProperty(value = "银行账户")
    private String bankAccount;

    @ApiModelProperty(value = "收票人姓名")
    private String receiverReceiptName;

    @ApiModelProperty(value = "收票人邮箱")
    private String receiverReceiptEmail;

    @ApiModelProperty(value = "收票人电话")
    private String receiverReceiptMobile;

    @NotBlank(message = "收票人地址不能为空")
    @ApiModelProperty(value = "地址名称， '，'分割")
    private String receiverReceiptAddressPath;

    @NotBlank(message = "收票人地址不能为空")
    @ApiModelProperty(value = "地址id，'，'分割 ")
    private String receiverReceiptAddressIdPath;

    @ApiModelProperty(value = "收票人详细地址")
    private String receiverReceiptAddress;

    @ApiModelProperty(value = "发票附件")
    private String receiptFile;

    @ApiModelProperty(value = "发货单号")
    private String logisticsNo;

    @ApiModelProperty(value = "物流公司Id")
    private String logisticsId;

    @ApiModelProperty(value = "物流公司名称")
    private String logisticsName;
}
