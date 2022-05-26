package cn.lili.modules.store.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 店铺发票设置
 *
 * @author pikachu
 * @since 2020-08-22 15:10:51
 */
@Data
public class StoreReceiptSettingDTO {

    @ApiModelProperty(value = "电子发票状态")
    private String electronicStatus;

    @ApiModelProperty(value = "增值税专用发票状态")
    private String vatSpecialStatus;

    @ApiModelProperty(value = "增值税普通发票状态")
    private String vatStatus;
}
