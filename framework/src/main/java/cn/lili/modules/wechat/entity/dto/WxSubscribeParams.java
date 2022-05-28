package cn.lili.modules.wechat.entity.dto;

import cn.lili.common.utils.StringUtils;
import cn.lili.common.vo.PageVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ftyy
 * @description: 微信公众号关注列表查询条件
 */
@Data
public class WxSubscribeParams extends PageVO {

    @ApiModelProperty(value = "关注者状态")
    private String subscribeStatus;

    @ApiModelProperty(value = "用户关注的渠道来源")
    private String subscribeScene;

    public <T> QueryWrapper<T> queryWrapper(){
        QueryWrapper<T> query = Wrappers.query();
        query.eq(StringUtils.isNotEmpty(subscribeStatus),"subscribe_status",subscribeStatus);
        query.eq(StringUtils.isNotEmpty(subscribeScene),"subscribe_scene",subscribeScene);
        return query;
    }
}
