package cn.lili.modules.order.cart.render.impl;

import cn.hutool.json.JSONUtil;
import cn.lili.common.enums.SwitchEnum;
import cn.lili.modules.member.entity.dos.MemberReceipt;
import cn.lili.modules.member.service.MemberReceiptService;
import cn.lili.modules.order.cart.entity.dto.TradeDTO;
import cn.lili.modules.order.cart.entity.enums.RenderStepEnums;
import cn.lili.modules.order.cart.render.CartRenderStep;
import cn.lili.modules.order.order.entity.enums.ReceiptTypeEnum;
import cn.lili.modules.store.entity.dos.StoreDetail;
import cn.lili.modules.store.entity.enums.ReceiptSourceEnum;
import cn.lili.modules.store.service.StoreDetailService;
import cn.lili.modules.system.entity.dos.Setting;
import cn.lili.modules.system.entity.dto.ReceiptSetting;
import cn.lili.modules.system.entity.enums.SettingEnum;
import cn.lili.modules.system.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 发票类型筛选 生成
 *
 * @author Chopper
 * @since 2020-07-02 14:47
 */
@Service
public class ReceiptTypeRender implements CartRenderStep {

    @Autowired
    private StoreDetailService storeDetailService;

    @Autowired
    private SettingService settingService;

    @Autowired
    private MemberReceiptService memberReceiptService;


    @Override
    public RenderStepEnums step() {
        return RenderStepEnums.RECEIPT_TYPE;
    }

    @Override
    public void render(TradeDTO tradeDTO) {
        //组织店铺id
        List<String> storeIds = tradeDTO.getCartList().stream().map(cartVO -> {
            return cartVO.getStoreId();
        }).collect(Collectors.toList());
        //如果是平台开票，则获取平台开票设置
        Setting receipt = settingService.get(SettingEnum.RECEIPT_SETTING.name());
        ReceiptSetting receiptSetting = JSONUtil.toBean(receipt.getSettingValue(), ReceiptSetting.class);
        //批量获取店铺信息
        List<StoreDetail> storeDetails = storeDetailService.getDetailList(storeIds);
        List<StoreDetail> newList = storeDetails.stream().map(store -> {
            store.setElectronicStatus(receiptSetting.getElectronicStatus());
            store.setVatStatus(receiptSetting.getVatStatus());
            store.setVatSpecialStatus(receipt.getSettingValue());
            return store;
        }).filter(storeDetail -> storeDetail.getReceiptSource().equals(ReceiptSourceEnum.PLATFORM.name())).collect(Collectors.toList());

        //筛选店铺共同开启的发票类型
        //电子普通发票
        Boolean electronicStatus = true;
        //增值税普通发票
        Boolean vatStatus = true;
        //增值税专用发票
        Boolean vatSpecialStatus = true;
        for (StoreDetail storeDetail : newList) {
            //只要有一个店铺电子普通发票关闭，则整单不允许适用此发票
            if (storeDetail.getElectronicStatus().equals(SwitchEnum.CLOSE.name())) {
                electronicStatus = false;
            }
            //只要有一个店铺增值税普通发票关闭，则整单不允许适用此发票
            if (storeDetail.getVatStatus().equals(SwitchEnum.CLOSE.name())) {
                vatStatus = false;
            }
            //只要有一个店铺增值税专用发票关闭，则整单不允许适用此发票
            if (storeDetail.getVatSpecialStatus().equals(SwitchEnum.CLOSE.name())) {
                vatSpecialStatus = false;
            }
        }
        List<String> receiptType = new ArrayList<>();
        if (electronicStatus) {
            receiptType.add(ReceiptTypeEnum.ELECTRONIC.name());
        }
        if (vatStatus) {
            receiptType.add(ReceiptTypeEnum.VAT.name());
        }
        if (vatSpecialStatus) {
            receiptType.add(ReceiptTypeEnum.VAT_SPECIAL.name());
        }
        //填充可开发票数据
        tradeDTO.setReceiptType(receiptType);
        //组织数据

    }
}
