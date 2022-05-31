package cn.lili.controller.wechat;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.wechat.entity.dos.WxSubscribe;
import cn.lili.modules.wechat.entity.dto.WxSubscribeParams;
import cn.lili.modules.wechat.service.WxSubscribeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ftyy
 * @description: 微信公众号关注
 */
@Api(tags = "微信公众号关注")
@RestController
@RequestMapping("/manager/wechat/wxSubscribe")
public class WxSubscribeController {

    @Autowired
    private WxSubscribeService wxSubscribeService;

    @GetMapping(value = "/init")
    @ApiOperation(value = "初始化关注微信公众号用户")
    public ResultMessage init() {
        wxSubscribeService.init();
        return ResultUtil.success();
    }


    @GetMapping
    @ApiOperation(value = "分页获取微信公众号关注列表")
    public ResultMessage<IPage<WxSubscribe>> getByPage(WxSubscribeParams wxSubscribeParams) {
        return ResultUtil.data(wxSubscribeService.getByPage(wxSubscribeParams));
    }
}
