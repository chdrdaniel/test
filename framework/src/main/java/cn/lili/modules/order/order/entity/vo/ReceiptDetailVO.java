package cn.lili.modules.order.order.entity.vo;

import cn.lili.modules.system.entity.vo.Traces;
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
@ApiModel(value = "开具发票VO")
public class ReceiptDetailVO extends ReceiptVO implements Serializable {


    private static final long serialVersionUID = 8231264087667194363L;

    @ApiModelProperty(value = "物流信息")
    private Traces traces;

}
