package cn.lili.modules.wechat.service;

import cn.lili.modules.wechat.entity.dos.WxUserStatistics;
import cn.lili.modules.wechat.entity.dto.WxStatisticsParams;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author: ftyy
 * @description: 微信统计业务层
 */
public interface WechatStatisticsService extends IService<WxUserStatistics> {
    /**
     * 查询微信用户统计
     * @param wxStatisticsParams
     * @return
     */
    List<WxUserStatistics> getWxUserStatistics(WxStatisticsParams wxStatisticsParams);
}
