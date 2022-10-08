package cn.lili.modules.order.order.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 发票
 *
 * @author Bulbasaur
 * @since 2020/11/28 11:38
 */
@Data
@ApiModel(value = "开具发票VO")
public class ReceiptInvoicingDTO implements Serializable {


    private static final long serialVersionUID = -3650195866734617549L;

    @ApiModelProperty(value = "发票id")
    private String id;

    @ApiModelProperty(value = "发票附件")
    private List<String> receiptFiles;

    @ApiModelProperty(value = "发货单号")
    private String logisticsNo;

    @ApiModelProperty(value = "物流公司Id")
    private String logisticsId;

}
