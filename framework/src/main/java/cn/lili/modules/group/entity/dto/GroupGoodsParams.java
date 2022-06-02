package cn.lili.modules.group.entity.dto;


import cn.hutool.core.text.CharSequenceUtil;
import cn.lili.common.vo.PageVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 团购商品查询
 *
 * @author chc
 * @since 2022-5-20 15:04:15
 */
@ApiModel
@Data
public class GroupGoodsParams extends PageVO {
    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("店铺Id")
    private String storeId;

    public <T> QueryWrapper<T> queryWrapper() {
        QueryWrapper<T> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(CharSequenceUtil.isNotEmpty(goodsName),"goods_name",goodsName);
        queryWrapper.eq(CharSequenceUtil.isNotEmpty(storeId),"store_id",storeId);
        queryWrapper.orderByDesc("create_time");

        return queryWrapper;
    }
}
