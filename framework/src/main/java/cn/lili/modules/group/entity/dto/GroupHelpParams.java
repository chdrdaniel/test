package cn.lili.modules.group.entity.dto;

import cn.hutool.core.text.CharSequenceUtil;
import cn.lili.common.vo.PageVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiModelProperty;
import cn.lili.modules.group.entity.enums.GroupHelpStatusEnums;
import lombok.Data;

/**
 * 帮卖会员查询
 *
 * @author chc
 * @since 2022-5-20 15:04:15
 */
@Data
public class GroupHelpParams extends PageVO {

    @ApiModelProperty("会员名称")
    private String memberName;

    /**
     * @see GroupHelpStatusEnums
     */
    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("店铺Id")
    private String storeId;

    public <T>QueryWrapper<T> queryWrapper(){
        QueryWrapper<T> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(CharSequenceUtil.isNotEmpty(memberName),"member_name",memberName);
        queryWrapper.eq("status",status);
        queryWrapper.eq("store_id",storeId);
        queryWrapper.orderByDesc("create_time");
        return queryWrapper;
    }
}
