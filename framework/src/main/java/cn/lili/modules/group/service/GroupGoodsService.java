package cn.lili.modules.group.service;

import cn.lili.modules.group.entity.dos.GroupGoods;
import cn.lili.modules.group.entity.dto.GroupGoodsParams;
import cn.lili.modules.group.entity.vos.GroupGoodsVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 团购商品池业务层
 *
 * @author chc
 * @since 2022-5-23 09:09:31
 */
public interface GroupGoodsService extends IService<GroupGoods> {

    /**
     * 获取团购商品分页
     * @param groupGoodsParams 查询条件
     * @return 团购商品分页
     */
    IPage<GroupGoods> queryPage(GroupGoodsParams groupGoodsParams);

    /**
     * 获取团购商品信息
     * @param groupGoodsId 团购商品Id
     * @return 商品信息
     */
    GroupGoodsVO info(String groupGoodsId);

    /**
     * 添加商品
     * @param groupGoods 商品信息
     * @return 商品信息
     */
    GroupGoods add(GroupGoods groupGoods);

    /**
     * 修改团购商品信息
     * @param groupGoods 商品信息
     * @return 修改结果
     */
    Boolean updateGroupGoods(GroupGoods groupGoods);

    /**
     * 删除团购商品
     * @param groupGoodsId 商品信息
     * @return 删除结果
     */
    Boolean removeGroupGoods(String groupGoodsId);


}
