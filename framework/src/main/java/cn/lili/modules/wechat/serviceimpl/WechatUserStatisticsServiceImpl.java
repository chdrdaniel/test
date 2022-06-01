package cn.lili.modules.wechat.serviceimpl;

import cn.lili.modules.wechat.entity.dos.WxUserStatistics;
import cn.lili.modules.wechat.entity.dto.WxStatisticsParams;
import cn.lili.modules.wechat.mapper.WechatUserStatisticsMapper;
import cn.lili.modules.wechat.service.WechatStatisticsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ftyy
 * @description: 微信统计业务实现层
 */
@Service
public class WechatUserStatisticsServiceImpl extends ServiceImpl<WechatUserStatisticsMapper, WxUserStatistics> implements WechatStatisticsService {

    @Override
    public List<WxUserStatistics> getWxUserStatistics(WxStatisticsParams wxStatisticsParams) {
        return this.list(wxStatisticsParams.queryWrapper());
    }
}
