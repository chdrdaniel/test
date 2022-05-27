package cn.lili.controller.wechat;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.wechat.entity.dos.WechatMenu;
import cn.lili.modules.wechat.entity.vo.WechatMenuVO;
import cn.lili.modules.wechat.service.WxMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author: ftyy
 * @description: 微信公众号菜单管理
 */
@Api(tags = "微信公众号菜单管理")
@RestController
@RequestMapping("/manager/wechat/wxMenu")
public class WxMenuController {

    @Autowired
    private WxMenuService wxMenuService;


    @ApiOperation(value = "获取微信公众号所有菜单")
    @GetMapping("/tree")
    public ResultMessage<List<WechatMenuVO>> getAllMenuList() {
        return ResultUtil.data(wxMenuService.tree());
    }


    @GetMapping(value = "/init")
    @ApiOperation(value = "初始化微信公众号菜单")
    public ResultMessage init() {
        wxMenuService.init();
        return ResultUtil.success();
    }

    @PostMapping
    @ApiOperation(value = "新增微信公众号菜单")
    public ResultMessage<WechatMenu> save(WechatMenu wxMenu) {

        wxMenuService.saveWxMenu(wxMenu);
        return ResultUtil.data(wxMenu);
    }




}
