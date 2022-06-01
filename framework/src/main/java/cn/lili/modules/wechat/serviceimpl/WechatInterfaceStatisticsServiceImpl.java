package cn.lili.modules.wechat.serviceimpl;

import cn.lili.modules.wechat.entity.dos.WxInterfaceStatistics;
import cn.lili.modules.wechat.entity.dto.WxStatisticsParams;
import cn.lili.modules.wechat.mapper.WechatInterfaceStatisticsMapper;
import cn.lili.modules.wechat.service.WechatInterfaceStatisticsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ftyy
 * @description: 微信公众号接口统计业务实现层
 */
@Service
public class WechatInterfaceStatisticsServiceImpl extends ServiceImpl<WechatInterfaceStatisticsMapper, WxInterfaceStatistics>  implements WechatInterfaceStatisticsService {
    @Override
    public List<WxInterfaceStatistics> getWxInterfaceStatistics(WxStatisticsParams wxStatisticsParams) {
        return this.list(wxStatisticsParams.queryWrapper());
    }
}
