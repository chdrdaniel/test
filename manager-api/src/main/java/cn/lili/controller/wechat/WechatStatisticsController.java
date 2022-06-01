package cn.lili.controller.wechat;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.wechat.entity.dos.WxInterfaceStatistics;
import cn.lili.modules.wechat.entity.dos.WxUserStatistics;
import cn.lili.modules.wechat.entity.dto.WxStatisticsParams;
import cn.lili.modules.wechat.service.WechatInterfaceStatisticsService;
import cn.lili.modules.wechat.service.WechatStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: ftyy
 * @description: 微信公众号统计
 */
@Api(tags = "微信公众号统计")
@RestController
@RequestMapping("/manager/wechat/wxStatistics")
public class WechatStatisticsController {

    @Autowired
    private WechatStatisticsService wechatStatisticsService;

    @Autowired
    private WechatInterfaceStatisticsService wechatInterfaceStatisticsService;

    @ApiOperation(value = "获取微信公众号用户统计")
    @GetMapping("/getWxUserStatistics")
    public ResultMessage<List<WxUserStatistics>> getWxUserStatistics(WxStatisticsParams wxStatisticsParams) {
        return ResultUtil.data(wechatStatisticsService.getWxUserStatistics(wxStatisticsParams));
    }

    @ApiOperation(value = "获取微信公众号接口统计")
    @GetMapping("/getWxUserStatistics")
    public ResultMessage<List<WxInterfaceStatistics>> getWxInterfaceStatistics(WxStatisticsParams wxStatisticsParams) {
        return ResultUtil.data(wechatInterfaceStatisticsService.getWxInterfaceStatistics(wxStatisticsParams));
    }
}
