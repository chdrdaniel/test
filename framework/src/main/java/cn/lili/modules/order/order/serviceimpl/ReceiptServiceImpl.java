package cn.lili.modules.order.order.serviceimpl;

import cn.hutool.json.JSONUtil;
import cn.lili.common.enums.ResultCode;
import cn.lili.common.exception.ServiceException;
import cn.lili.common.utils.StringUtils;
import cn.lili.modules.member.service.StoreLogisticsService;
import cn.lili.modules.order.order.entity.dos.Order;
import cn.lili.modules.order.order.entity.enums.ReceiptTypeEnum;
import cn.lili.modules.order.order.entity.vo.OrderReceiptVO;
import cn.lili.modules.order.order.entity.dto.ReceiptInvoicingDTO;
import cn.lili.modules.order.order.service.OrderService;
import cn.lili.modules.store.entity.enums.ReceiptSourceEnum;
import cn.lili.modules.system.entity.dos.Logistics;
import cn.lili.modules.system.entity.vo.StoreLogisticsVO;
import cn.lili.modules.system.entity.vo.Traces;
import cn.lili.modules.system.service.LogisticsService;
import cn.lili.mybatis.util.PageUtil;
import cn.lili.common.vo.PageVO;
import cn.lili.modules.order.order.entity.dos.Receipt;
import cn.lili.modules.order.order.entity.dto.OrderReceiptDTO;
import cn.lili.modules.order.order.entity.dto.ReceiptSearchParams;
import cn.lili.modules.order.order.mapper.ReceiptMapper;
import cn.lili.modules.order.order.service.ReceiptService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 发票业务层实现
 *
 * @author Bulbasaur
 * @since 2020/11/17 7:38 下午
 */
@Service
public class ReceiptServiceImpl extends ServiceImpl<ReceiptMapper, Receipt> implements ReceiptService {

    private static final String ORDER_SN_COLUMN = "order_sn";

    @Autowired
    private OrderService orderService;


    /**
     * 物流公司
     */
    @Autowired
    private LogisticsService logisticsService;

    @Override
    public IPage<OrderReceiptDTO> getReceiptData(ReceiptSearchParams searchParams, PageVO pageVO) {
        return this.baseMapper.getReceipt(PageUtil.initPage(pageVO), searchParams.wrapper());
    }

    @Override
    public Receipt getByOrderSn(String orderSn) {
        LambdaQueryWrapper<Receipt> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Receipt::getOrderSn, orderSn);
        return this.getOne(lambdaQueryWrapper);
    }

    @Override
    public Receipt getDetail(String id) {
        return this.getById(id);
    }

    @Override
    public Receipt saveReceipt(Receipt receipt) {
        LambdaQueryWrapper<Receipt> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Receipt::getReceiptTitle, receipt.getReceiptTitle());
        queryWrapper.eq(Receipt::getMemberId, receipt.getMemberId());
        if (receipt.getId() != null) {
            queryWrapper.ne(Receipt::getId, receipt.getId());
        }
        if (this.getOne(queryWrapper) == null) {
            this.save(receipt);
            return receipt;
        }
        return null;
    }

    @Override
    public Receipt invoicing(ReceiptInvoicingDTO receiptInvoicingDTO) {
        //根据id查询发票信息
        Receipt receipt = this.getById(receiptInvoicingDTO.getId());
        if (receipt != null) {
            receipt.setReceiptStatus(1);
            //如果发票附件存在则保存
            if (receiptInvoicingDTO.getReceiptFiles() != null && receiptInvoicingDTO.getReceiptFiles().size() > 0) {
                receipt.setReceiptFile(JSONUtil.toJsonStr(receiptInvoicingDTO.getReceiptFiles()));
            }
            //如果发票物流不为空则保存发票物流
            if (StringUtils.isNotEmpty(receiptInvoicingDTO.getLogisticsId())) {
                //获取对应物流
                Logistics logistics = logisticsService.getById(receiptInvoicingDTO.getLogisticsId());
                if (logistics == null) {
                    throw new ServiceException(ResultCode.ORDER_LOGISTICS_ERROR);
                }
                receipt.setLogisticsId(logistics.getId());
                receipt.setLogisticsName(logistics.getName());
            }

            receipt.setLogisticsNo(receiptInvoicingDTO.getLogisticsNo());
            this.saveOrUpdate(receipt);
            return receipt;
        }
        throw new ServiceException(ResultCode.USER_RECEIPT_NOT_EXIST);
    }

    @Override
    public OrderReceiptVO getOrderReceipt(String receiptId) {

        //查询发票信息
        Receipt receipt = this.getById(receiptId);
        if (receipt == null) {
            return null;
        }
        Order order = orderService.getBySn(receipt.getOrderSn());
        if (order == null) {
            throw new ServiceException(ResultCode.ORDER_NOT_EXIST);
        }
        OrderReceiptVO orderReceiptVO = new OrderReceiptVO(order, receipt);

        //如果发票物流信息不为空 则查询物流信息
        if (StringUtils.isNotEmpty(receipt.getLogisticsNo())) {
            Traces traces = logisticsService.getLogistic(receipt.getLogisticsId(), receipt.getLogisticsNo(), "");
            orderReceiptVO.getReceipt().setTraces(traces);
        }
        return orderReceiptVO;
    }
}