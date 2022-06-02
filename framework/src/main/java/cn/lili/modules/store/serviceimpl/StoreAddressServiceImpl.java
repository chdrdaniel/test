package cn.lili.modules.store.serviceimpl;

import cn.lili.common.security.OperationalJudgment;
import cn.lili.common.security.context.UserContext;
import cn.lili.common.vo.PageVO;
import cn.lili.modules.store.entity.dos.StoreAddress;
import cn.lili.modules.store.entity.dto.StoreAddressParams;
import cn.lili.modules.store.mapper.StoreAddressMapper;
import cn.lili.modules.store.service.StoreAddressService;
import cn.lili.mybatis.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 店铺地址（自提点）业务层实现
 *
 * @author Bulbasaur
 * @since 2020/11/22 16:00
 */
@Service
public class StoreAddressServiceImpl extends ServiceImpl<StoreAddressMapper, StoreAddress> implements StoreAddressService {

    @Override
    public IPage<StoreAddress> getStoreAddress(StoreAddressParams storeAddressParams) {
        //获取当前登录商家账号
        return this.page(PageUtil.initPage(storeAddressParams),storeAddressParams.wrapper());
    }

    @Override
    public StoreAddress editStoreAddress(String storeId, StoreAddress storeAddress) {
        //获取当前登录商家账号
        storeAddress.setStoreId(storeId);
        //添加自提点
        this.updateById(storeAddress);
        return storeAddress;
    }

    @Override
    public StoreAddress addStoreAddress(StoreAddress storeAddress) {
        String storeId = Objects.requireNonNull(UserContext.getCurrentUser()).getStoreId();
        storeAddress.setStoreId(storeId);
        storeAddress.setOrderNumber(0);
        storeAddress.setSalesVolume(0);
        this.save(storeAddress);
        return storeAddress;
    }

    @Override
    public Boolean removeStoreAddress(String id) {
        return this.removeById(id);
    }

    @Override
    public Boolean abandon(List<String> ids) {
        //修改自提点状态,作废后清空自提点团长
        return this.update(new LambdaUpdateWrapper<StoreAddress>().in(StoreAddress::getId,ids).set(StoreAddress::getDeleteFlag,true).set(StoreAddress::getHeadId,"").set(StoreAddress::getHeadName,"").set(StoreAddress::getHeadFace,""));
    }

    @Override
    public Boolean recovery(String id) {
        return this.update(new LambdaUpdateWrapper<StoreAddress>().eq(StoreAddress::getId,id).set(StoreAddress::getDeleteFlag,false));
    }
}