package cn.lili.controller.wechat;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.PageVO;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.wechat.entity.dos.WxTemplateMessage;
import cn.lili.modules.wechat.entity.dto.WxSendTemplateParams;
import cn.lili.modules.wechat.service.WxTemplateMessageService;
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
 * @description: 微信公众号消息模板
 */

@Api(tags = "微信公众号消息模板")
@RestController
@RequestMapping("/manager/wechat/wxSendTemplate")
public class WxSendTemplateController {

    @Autowired
    private WxTemplateMessageService wxTemplateMessageService;

    @GetMapping(value = "/sendTemplateMessage")
    @ApiOperation(value = "发送模板消息")
    public ResultMessage<WxTemplateMessage> sendTemplateMessage(WxSendTemplateParams wxSendTemplateParams) {
        wxTemplateMessageService.sendTemplateMessage(wxSendTemplateParams);
        return ResultUtil.success();
    }

    @GetMapping(value = "/getByPage")
    @ApiOperation(value = "分页获取微信公众号模板消息")
    public ResultMessage<IPage<WxTemplateMessage>> getByPage(PageVO page) {
        return ResultUtil.data(wxTemplateMessageService.page(PageUtil.initPage(page)));
    }

    @PutMapping
    @ApiOperation(value = "修改微信公众号模板消息")
    public ResultMessage<WxTemplateMessage> updateWechatMenu(@Valid WxTemplateMessage wxTemplateMessage) {
        wxTemplateMessageService.saveOrUpdate(wxTemplateMessage);
        return ResultUtil.data(wxTemplateMessage);
    }

    @PostMapping
    @ApiOperation(value = "新增微信公众号模板消息")
    public ResultMessage<WxTemplateMessage> save(WxTemplateMessage wxTemplateMessage) {
        wxTemplateMessageService.saveOrUpdate(wxTemplateMessage);
        return ResultUtil.data(wxTemplateMessage);
    }

    @DeleteMapping(value = "/{ids}")
    @ApiOperation(value = "删除微信公众号模板消息")
    public ResultMessage<Object> delAllByIds(@PathVariable List ids) {
        wxTemplateMessageService.removeByIds(ids);
        return ResultUtil.success();
    }
}
