package cn.lili.modules.order.order.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 发票
 *
 * @author Bulbasaur
 * @since 2020/11/28 11:38
 */
@Data
@ApiModel(value = "发票")
public class ReceiptVO implements Serializable {

    private static final long serialVersionUID = -8402457457074092957L;

    /**
     * @see cn.lili.modules.order.order.entity.enums.ReceiptTypeEnum
     */

    @ApiModelProperty(value = "发票类型")
    private String receiptType;

    @ApiModelProperty(value = "发票抬头")
    private String receiptTitle;

    @ApiModelProperty(value = "开票方式")
    private String receiptMethod;

    @ApiModelProperty(value = "公司名称  如果是个人则是个人名称")
    private String companyName;

    @ApiModelProperty(value = "纳税人识别号")
    private String taxpayerId;

    @ApiModelProperty(value = "发票内容")
    private String receiptContent;

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

    @ApiModelProperty(value = "地址名称， '，'分割")
    private String receiverReceiptAddressPath;

    @ApiModelProperty(value = "地址id，'，'分割 ")
    private String receiverReceiptAddressIdPath;

    @ApiModelProperty(value = "收票人详细地址")
    private String receiverReceiptAddress;

    /**
     * @see cn.lili.modules.store.entity.enums.ReceiptSourceEnum
     */
    @ApiModelProperty(value = "发票开具方")
    private String receiptSource;

}
