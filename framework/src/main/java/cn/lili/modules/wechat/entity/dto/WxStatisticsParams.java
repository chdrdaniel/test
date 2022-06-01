package cn.lili.modules.wechat.entity.dto;

import cn.lili.common.utils.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author: ftyy
 * @description: 微信用户统计
 */
public class WxStatisticsParams {

    @ApiModelProperty(value = "开始时间")
    private Date beginDate;

    @ApiModelProperty(value = "截止时间")
    private Date endDate;

    public <T> QueryWrapper<T> queryWrapper(){
        QueryWrapper<T> query = Wrappers.query();
        if (beginDate != null && endDate != null) {
            query.between("create_time", beginDate, endDate);
        }else{
            query.between("create_time",  DateUtil.getSomeTime(6), DateUtil.getSomeTime(1));
        }
        return query;
    }
}
