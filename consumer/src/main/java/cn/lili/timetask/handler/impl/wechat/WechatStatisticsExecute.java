package cn.lili.timetask.handler.impl.wechat;

import cn.lili.common.utils.DateUtil;
import cn.lili.modules.wechat.entity.dos.WxInterfaceStatistics;
import cn.lili.modules.wechat.entity.dos.WxUserStatistics;
import cn.lili.modules.wechat.service.WechatInterfaceStatisticsService;
import cn.lili.modules.wechat.service.WechatStatisticsService;
import cn.lili.timetask.handler.EveryDayExecute;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeInterfaceResult;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeUserCumulate;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeUserSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author: ftyy
 * @description: 微信公众号统计
 */
@Component
public class WechatStatisticsExecute implements EveryDayExecute {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatStatisticsService wechatStatisticsService;

    @Autowired
    private WechatInterfaceStatisticsService wechatInterfaceStatisticsService;

    @Override
    public void execute() {
        //同步昨日微信用户统计
         this.updateWxUserStatistics();
        //同步昨日微信接口统计
        this.updateWxInterfaceStatistics();
    }

    /**
     * 同步昨日微信用户统计
     */
    public void updateWxUserStatistics() {
        try {
            //时间
            Date synchronizationDate = DateUtil.getSomeTime(1);
            //获取昨日用户增减数据统计
            List<WxDataCubeUserSummary> userSummary = wxMpService.getDataCubeService().getUserSummary(synchronizationDate, synchronizationDate);
            //获取昨日累计用户数据统计
            List<WxDataCubeUserCumulate> userCumulate = wxMpService.getDataCubeService().getUserCumulate(synchronizationDate, synchronizationDate);
            //微信用户统计
            WxUserStatistics wxUserStatistics = WxUserStatistics.builder().userSource(userSummary.get(0).getUserSource()).newUser(userSummary.get(0).getNewUser()).cancelUser(userSummary.get(0).getCancelUser()).cumulateUser(userCumulate.get(0).getCumulateUser()).build();
            wxUserStatistics.setCreateTime(userSummary.get(0).getRefDate());
            wechatStatisticsService.save(wxUserStatistics);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }


    public void updateWxInterfaceStatistics() {
        try {
            //时间
            Date synchronizationDate = DateUtil.getSomeTime(1);
            //获取昨日接口分析数据统计
            List<WxDataCubeInterfaceResult> interfaceSummary = wxMpService.getDataCubeService().getInterfaceSummary(synchronizationDate, synchronizationDate);
            //获取接口分析分时数据
            List<WxDataCubeInterfaceResult> interfaceSummaryHour = wxMpService.getDataCubeService().getInterfaceSummaryHour(synchronizationDate, synchronizationDate);

            WxInterfaceStatistics wxInterfaceStatistics = new WxInterfaceStatistics();
            //设置默认时间
            wxInterfaceStatistics.setCreateTime(new Date());
            //检验昨日接口分析数据统计不为空
            if (!interfaceSummary.isEmpty() && interfaceSummary.size() > 0) {
                wxInterfaceStatistics.setCallbackCount(interfaceSummary.get(0).getCallbackCount());
                wxInterfaceStatistics.setFailCount(interfaceSummary.get(0).getFailCount());
                wxInterfaceStatistics.setTotalTimeCost(interfaceSummary.get(0).getTotalTimeCost());
                wxInterfaceStatistics.setMaxTimeCost(interfaceSummary.get(0).getMaxTimeCost());
                wxInterfaceStatistics.setCreateTime(DateUtil.toDate(interfaceSummary.get(0).getRefDate(), null));
            }
            //检验接口分析分时数据不为空
            if (!interfaceSummaryHour.isEmpty() && interfaceSummaryHour.size() > 0) {
                wxInterfaceStatistics.setRefHour(interfaceSummaryHour.get(0).getRefHour());
            }

            wechatInterfaceStatisticsService.save(wxInterfaceStatistics);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
