package cn.lili.timetask.handler.impl.wechat;

import cn.lili.common.utils.CurrencyUtil;
import cn.lili.common.utils.DateUtil;
import cn.lili.modules.wechat.entity.dos.WxInterfaceStatistics;
import cn.lili.modules.wechat.entity.dos.WxUserLabel;
import cn.lili.modules.wechat.entity.dos.WxUserStatistics;
import cn.lili.modules.wechat.service.WechatInterfaceStatisticsService;
import cn.lili.modules.wechat.service.WechatStatisticsService;
import cn.lili.modules.wechat.service.WxMaterialUploadService;
import cn.lili.modules.wechat.service.WxUserLabelService;
import cn.lili.timetask.handler.EveryDayExecute;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeInterfaceResult;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeUserCumulate;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeUserSummary;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNewsBatchGetResult;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    @Autowired
    private WxUserLabelService wxUserLabelService;

    @Autowired
    private WxMaterialUploadService wxMaterialUploadService;

    @Override
    public void execute() {
        //同步昨日微信用户统计
        this.updateWxUserStatistics();
        //同步昨日微信接口统计
        this.updateWxInterfaceStatistics();
        //同步微信公众号标签列表
        this.updateWxUserLabel();
        //定时同步图文素材
        this.materialUpload();
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


    /**
     * 同步昨日微信接口统计
     */
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

    /**
     * 同步微信公众号标签列表
     */
    public void updateWxUserLabel(){
        try {
            //获取微信公众号所有标签
            List<WxUserTag> wxUserTagList = wxMpService.getUserTagService().tagGet();
            List<WxUserLabel> wxUserLabelList =new ArrayList();

            if(!wxUserTagList.isEmpty() && wxUserTagList.size()>0){
                wxUserTagList.stream().forEach(wxUserTag->{
                    WxUserLabel wxUserLabel = new WxUserLabel();
                    wxUserLabel.setId(String.valueOf(wxUserTag.getId()));
                    wxUserLabel.setName(wxUserTag.getName());
                    wxUserLabel.setCount(wxUserTag.getCount());
                    wxUserLabelList.add(wxUserLabel);
                });
                wxUserLabelService.saveOrUpdateBatch(wxUserLabelList);
            }


        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    public void materialUpload(){
        try {
            //获取微信公众号图文信息
            WxMpMaterialNewsBatchGetResult wxMpMaterialNewsBatchGetResult = this.wxMpService.getMaterialService().materialNewsBatchGet(0, 20);
            //校验微信公众号素材不为空
            if(wxMpMaterialNewsBatchGetResult!=null
                    && wxMpMaterialNewsBatchGetResult.getTotalCount()>0
                    && !wxMpMaterialNewsBatchGetResult.getItems().isEmpty()
                    && wxMpMaterialNewsBatchGetResult.getItems().size()>0){
                //添加初次请求数据
                wxMaterialUploadService.saveList(wxMpMaterialNewsBatchGetResult.getItems());
                //计算总页数（总条数/条数）
                Integer pageNumber = Integer.valueOf((int) CurrencyUtil.div(wxMpMaterialNewsBatchGetResult.getTotalCount(), 20));
                //总条数不为整数时 多请求一次
                if(wxMpMaterialNewsBatchGetResult.getTotalCount() % 2==1){
                    pageNumber=++pageNumber;
                }
                //循环请求
                for (int i = wxMpMaterialNewsBatchGetResult.getItemCount()-1; i <= pageNumber; i=i+20) {
                    //获取微信公众号图文信息
                    WxMpMaterialNewsBatchGetResult wxMpMaterialNewsBatchGet = this.wxMpService.getMaterialService().materialNewsBatchGet(i, 20);
                    wxMaterialUploadService.saveList(wxMpMaterialNewsBatchGet.getItems());
                }

            }

        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
