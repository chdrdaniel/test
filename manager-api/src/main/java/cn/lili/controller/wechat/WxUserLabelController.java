package cn.lili.controller.wechat;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.PageVO;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.wechat.entity.dos.WxSubscribe;
import cn.lili.modules.wechat.entity.dos.WxTemplateMessage;
import cn.lili.modules.wechat.entity.dos.WxUserLabel;
import cn.lili.modules.wechat.entity.dto.WxSubscribeParams;
import cn.lili.modules.wechat.service.WxUserLabelService;
import cn.lili.mybatis.util.PageUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: ftyy
 * @description: 微信公众号用户标签
 */
@Api(tags = "微信公众号用户标签")
@RestController
@RequestMapping("/manager/wechat/wxUserLabel")
public class WxUserLabelController {

    @Autowired
    private WxUserLabelService wxUserLabelService;

    @GetMapping(value = "/getByPage")
    @ApiOperation(value = "分页获取微信公众号用户标签")
    public ResultMessage<IPage<WxUserLabel>> getByPage(PageVO page) {
        return ResultUtil.data(wxUserLabelService.page(PageUtil.initPage(page)));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除微信公众号用户标签")
    public ResultMessage<Object> delAllById(@PathVariable String id) {
        wxUserLabelService.delAllById(id);
        return ResultUtil.success();
    }

    @PostMapping(value = "/saveWxUserLabel")
    @ApiOperation(value = "新增微信公众号用户标签")
    public ResultMessage<WxUserLabel> saveWxUserLabel(WxUserLabel wxUserLabel) {
        wxUserLabelService.saveWxUserLabel(wxUserLabel);
        return ResultUtil.data(wxUserLabel);
    }

    @PutMapping(value = "/updateWxUserLabel")
    @ApiOperation(value = "修改微信公众号用户标签")
    public ResultMessage<WxUserLabel> updateWxUserLabel(@Valid WxUserLabel wxUserLabel) {
        wxUserLabelService.updateWxUserLabel(wxUserLabel);
        return ResultUtil.data(wxUserLabel);
    }

    @PostMapping(value = "/updateUserLabel")
    @ApiOperation(value = "微信用户增加标签")
    public ResultMessage<Object> updateUserLabel(String wxUserId,String ids) {
        wxUserLabelService.updateUserLabel(wxUserId,ids);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "/deleteUserLabel")
    @ApiOperation(value = "微信用户删除标签")
    public ResultMessage<Object> deleteUserLabel(String wxUserId,String ids) {
        wxUserLabelService.deleteUserLabel(wxUserId,ids);
        return ResultUtil.success();
    }
}
