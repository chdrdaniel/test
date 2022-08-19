package cn.lili.modules.order.order.serviceimpl;

import cn.lili.common.enums.ResultCode;
import cn.lili.common.exception.ServiceException;
import cn.lili.modules.member.service.StoreLogisticsService;
import cn.lili.modules.order.order.entity.dos.Order;
import cn.lili.modules.order.order.entity.dos.OrderItem;
import cn.lili.modules.order.order.entity.enums.ReceiptTypeEnum;
import cn.lili.modules.order.order.entity.vo.OrderDetailVO;
import cn.lili.modules.order.order.entity.vo.OrderReceiptVO;
import cn.lili.modules.order.order.mapper.OrderItemMapper;
import cn.lili.modules.order.order.service.OrderService;
import cn.lili.modules.order.trade.entity.dos.OrderLog;
import cn.lili.modules.store.entity.enums.ReceiptSourceEnum;
import cn.lili.modules.store.entity.vos.StoreVO;
import cn.lili.modules.store.service.StoreService;
import cn.lili.modules.system.entity.vo.StoreLogisticsVO;
import cn.lili.mybatis.util.PageUtil;
import cn.lili.common.vo.PageVO;
import cn.lili.modules.order.order.entity.dos.Receipt;
import cn.lili.modules.order.order.entity.dto.OrderReceiptDTO;
import cn.lili.modules.order.order.entity.dto.ReceiptSearchParams;
import cn.lili.modules.order.order.mapper.ReceiptMapper;
import cn.lili.modules.order.order.service.ReceiptService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Autowired
    private StoreLogisticsService storeLogisticsService;

    @Resource
    private OrderItemMapper orderItemMapper;

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
    public Receipt invoicing(String receiptId) {
        //根据id查询发票信息
        Receipt receipt = this.getById(receiptId);
        if (receipt != null) {
            receipt.setReceiptStatus(1);
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
        //如果需要发货 则查询物流公司信息
        if (receipt.getReceiptSource().equals(ReceiptSourceEnum.STORE.value())
                /*&& receipt.getReceiptStatus() == 0
                && receipt.getReceiptType().equals(ReceiptTypeEnum.VAT_SPECIAL.name())*/) {
            List<StoreLogisticsVO> storeLogisticsVOS = storeLogisticsService.getStoreSelectedLogistics(receipt.getStoreId());
            orderReceiptVO.setStoreLogisticsVOS(storeLogisticsVOS);
        }
        return orderReceiptVO;
    }
}