package cn.lili.modules.member.entity.dos;

import cn.lili.modules.order.order.entity.vo.ReceiptVO;
import cn.lili.mybatis.BaseIdEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 会员发票
 *
 * @author Chopper
 * @since 2021-03-29 14:10:16
 */
@Data
@TableName("li_member_receipt")
@ApiModel(value = "会员发票")
public class MemberReceipt extends BaseIdEntity {

    private static final long serialVersionUID = -8210927482915675995L;

    @ApiModelProperty(value = "公司名称  如果是个人则是个人名称")
    private String companyName;

    @ApiModelProperty(value = "发票抬头")
    private String receiptTitle;

    @ApiModelProperty(value = "纳税人识别号")
    private String taxpayerId;

    @ApiModelProperty(value = "发票内容")
    private String receiptContent;

    @ApiModelProperty(value = "会员ID")
    private String memberId;

    @ApiModelProperty(value = "会员名称")
    private String memberName;

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


    @ApiModelProperty(value = "是否为默认选项 0：否，1：是")
    private Boolean isDefault;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "删除标志 true/false 删除/未删除", hidden = true)
    private Boolean deleteFlag;


    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;



}
