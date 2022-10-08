package cn.lili.controller.order;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.security.OperationalJudgment;
import cn.lili.common.security.context.UserContext;
import cn.lili.common.vo.PageVO;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.order.order.entity.dos.Receipt;
import cn.lili.modules.order.order.entity.dto.OrderReceiptDTO;
import cn.lili.modules.order.order.entity.dto.ReceiptInvoicingDTO;
import cn.lili.modules.order.order.entity.dto.ReceiptSearchParams;
import cn.lili.modules.order.order.entity.enums.ReceiptTypeEnum;
import cn.lili.modules.order.order.entity.vo.OrderReceiptVO;
import cn.lili.modules.order.order.service.ReceiptService;
import cn.lili.modules.store.entity.enums.ReceiptSourceEnum;
import cn.lili.modules.system.entity.vo.StoreLogisticsVO;
import cn.lili.modules.system.service.LogisticsService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理端,发票记录接口
 *
 * @author paulG
 * @since 2020/11/17 4:34 下午
 **/
@RestController
@Api(tags = "管理端,发票记录接口")
@RequestMapping("/manager/order/receipt")
public class ReceiptManagerController {

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private LogisticsService logisticsService;


    @ApiOperation(value = "获取发票分页信息")
    @GetMapping
    public ResultMessage<IPage<OrderReceiptDTO>> getPage(ReceiptSearchParams searchParams, PageVO pageVO) {
        return ResultUtil.data(this.receiptService.getReceiptData(searchParams, pageVO));
    }


    @ApiOperation(value = "通过id获取")
    @ApiImplicitParam(name = "id", value = "发票ID", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/get/detail/{id}")
    public ResultMessage<OrderReceiptVO> getOrderReceipt(@PathVariable String id) {
        return ResultUtil.data(receiptService.getOrderReceipt(id));
    }

    @ApiOperation(value = "开发票")
    @ApiImplicitParam(name = "id", value = "发票ID", required = true, dataType = "String", paramType = "path")
    @PostMapping(value = "/{id}/invoicing")
    public ResultMessage<Receipt> invoicing(@PathVariable String id, ReceiptInvoicingDTO receiptInvoicingDTO) {
        OperationalJudgment.judgment(receiptService.getById(id));
        receiptInvoicingDTO.setId(id);
        return ResultUtil.data(receiptService.invoicing(receiptInvoicingDTO));
    }


}
