package cn.lili.modules.group.serviceimpl;

import cn.lili.common.security.AuthUser;
import cn.lili.common.security.context.UserContext;
import cn.lili.modules.goods.service.GoodsSkuService;
import cn.lili.modules.group.entity.dos.GroupGoods;
import cn.lili.modules.group.entity.dto.GroupGoodsParams;
import cn.lili.modules.group.entity.vos.GroupGoodsVO;
import cn.lili.modules.group.mapper.GroupGoodsMapper;
import cn.lili.modules.group.service.GroupGoodsService;
import cn.lili.mybatis.util.PageUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 团购商品池业务层处理
 *
 * @author chc
 * @since 2022-5-23 09:09:31
 */
@Service
public class GroupGoodsServiceImpl extends ServiceImpl<GroupGoodsMapper, GroupGoods> implements GroupGoodsService {

    @Autowired
    private GoodsSkuService goodsSkuService;

    @Override
    public GroupGoodsVO info(String groupGoodsId) {
        GroupGoodsVO groupGoodsVO=new GroupGoodsVO();
        GroupGoods groupGoods = this.getById(groupGoodsId);
        groupGoodsVO.setGoodsSku(goodsSkuService.getGoodsSkuByIdFromCache(groupGoods.getSkuId()));
        groupGoodsVO.setGroupGoods(groupGoods);
        return groupGoodsVO;
    }

    @Override
    public GroupGoods add(GroupGoods groupGoods) {
        AuthUser currentUser = UserContext.getCurrentUser();
        groupGoods.setStoreId(currentUser.getStoreId());
        groupGoods.setStoreName(currentUser.getStoreName());
        this.save(groupGoods);
        return groupGoods;
    }

    @Override
    public Boolean removeGroupGoods(String groupGoodsId) {
        return this.removeById(groupGoodsId);
    }

    @Override
    public Boolean updateGroupGoods(GroupGoods groupGoods) {
        return this.updateById(groupGoods);
    }

    @Override
    public IPage<GroupGoods> queryPage(GroupGoodsParams groupGoodsParams) {
        AuthUser currentUser = UserContext.getCurrentUser();
        groupGoodsParams.setStoreId(currentUser.getStoreId());
        return this.page(PageUtil.initPage(groupGoodsParams),groupGoodsParams.queryWrapper());
    }
}
