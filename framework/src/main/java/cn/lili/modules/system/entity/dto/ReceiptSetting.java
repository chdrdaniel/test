package cn.lili.modules.system.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品设置
 *
 * @author Chopper
 * @since 2020/11/17 7:58 下午
 */
@Data
public class ReceiptSetting implements Serializable {

    private static final long serialVersionUID = -4132785717175910025L;

    @ApiModelProperty(value = "电子发票状态")
    private String electronicStatus;

    @ApiModelProperty(value = "增值税专用发票状态")
    private String vatSpecialStatus;

    @ApiModelProperty(value = "增值税普通发票状态")
    private String vatStatus;

}
