package cn.lili.modules.wechat.service;


import cn.lili.modules.wechat.entity.dos.WxInterfaceStatistics;
import cn.lili.modules.wechat.entity.dto.WxStatisticsParams;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author: ftyy
 * @description: 微信公众号接口统计业务层
 */
public interface WechatInterfaceStatisticsService extends IService<WxInterfaceStatistics> {

    /**
     * 微信公众号接口统计列表
     * @param wxStatisticsParams
     * @return
     */
    List<WxInterfaceStatistics> getWxInterfaceStatistics(WxStatisticsParams wxStatisticsParams);
}
