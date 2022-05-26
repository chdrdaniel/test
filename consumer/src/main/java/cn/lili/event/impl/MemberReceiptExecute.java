package cn.lili.event.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.lili.common.utils.StringUtils;
import cn.lili.event.OrderStatusChangeEvent;
import cn.lili.event.TradeEvent;
import cn.lili.modules.member.entity.dos.MemberReceipt;
import cn.lili.modules.member.service.MemberReceiptService;
import cn.lili.modules.order.cart.entity.dto.TradeDTO;
import cn.lili.modules.order.order.entity.enums.ReceiptTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员发票处理
 *
 * @author Chopper
 * @since 2020-07-03 11:20
 **/
@Slf4j
@Service
public class MemberReceiptExecute implements TradeEvent {


    @Autowired
    private MemberReceiptService memberReceiptService;

    @Override
    public void orderCreate(TradeDTO tradeDTO) {
        //如果订单需要开发票并且开公司发票
        if (tradeDTO.getNeedReceipt() && StringUtils.isNotEmpty(tradeDTO.getReceiptVO().getTaxpayerId())) {
            MemberReceipt memberReceipt = new MemberReceipt();
            BeanUtil.copyProperties(tradeDTO.getReceiptVO(), memberReceipt);
            memberReceipt.setMemberId(tradeDTO.getMemberId());
            memberReceipt.setMemberName(tradeDTO.getMemberName());
            memberReceipt.setIsDefault(true);
            memberReceiptService.add(memberReceipt);
        }

    }


}
