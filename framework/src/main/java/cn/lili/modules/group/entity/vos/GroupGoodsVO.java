package cn.lili.modules.group.entity.vos;

import cn.lili.modules.goods.entity.dos.GoodsSku;
import cn.lili.modules.group.entity.dos.GroupGoods;
import lombok.Data;

/**
 * 团购商品VO
 *
 * @Author chc
 * @since 2022/5/26 17:38
 */
@Data
public class GroupGoodsVO {

    private GroupGoods groupGoods;

    private GoodsSku goodsSku;
}
