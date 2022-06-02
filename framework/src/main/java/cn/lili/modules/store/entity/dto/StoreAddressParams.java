package cn.lili.modules.store.entity.dto;

import cn.hutool.core.text.CharSequenceUtil;
import cn.lili.common.vo.PageVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author chc
 * @since 2022/6/1 16:05
 */
@Data
public class StoreAddressParams extends PageVO {
    @ApiModelProperty(value = "店铺id")
    private String storeId;

    @ApiModelProperty(value = "自提点名称")
    private String addressName;

    public <T> QueryWrapper<T> wrapper() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CharSequenceUtil.isNotEmpty(storeId),"store_id",storeId);
        queryWrapper.like(CharSequenceUtil.isNotEmpty(addressName),"address_name",addressName);
        return queryWrapper;
    }
}
