package cn.lili.controller.wechat;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.wechat.entity.dos.WechatMenu;
import cn.lili.modules.wechat.entity.vo.WechatMenuVO;
import cn.lili.modules.wechat.service.WxMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @PutMapping
    @ApiOperation(value = "修改微信公众号菜单")
    public ResultMessage<WechatMenu> updateWechatMenu(@Valid WechatMenu wxMenu) {
        wxMenuService.updateWechatMenu(wxMenu);
        return ResultUtil.data(wxMenu);
    }

    @DeleteMapping(value = "/{id}")
    @ApiImplicitParam(name = "id", value = "公众号菜单id", required = true, paramType = "path", dataType = "String")
    @ApiOperation(value = "通过微信公众号菜单")
    public ResultMessage<WechatMenu> delAllByIds(@NotNull @PathVariable String id) {
        wxMenuService.delAllByIds(id);
        return ResultUtil.success();
    }




}
