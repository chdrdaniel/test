package cn.lili.controller.group;

import cn.lili.common.aop.annotation.PreventDuplicateSubmissions;
import cn.lili.common.enums.ResultCode;
import cn.lili.common.enums.ResultUtil;
import cn.lili.common.exception.ServiceException;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.group.service.GroupHelpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 买家端,帮卖会员Api
 *
 * @Author chc
 * @since 2022/5/20 16:21
 */
@Api(tags = "买家端,帮卖会员Api")
@RestController
@RequestMapping("/buyer/group/help")
public class GroupHelpBuyerController {

    @Autowired
    private GroupHelpService groupHelpService;

    @PreventDuplicateSubmissions
    @ApiOperation("申请帮卖")
    @ApiImplicitParam(name = "storeId", value = "店铺Id", required = true, paramType = "path")
    @PostMapping("/apply/{storeId}")
    public ResultMessage<Object> applyHelp(@PathVariable("storeId") String storeId) {
        if(groupHelpService.apply(storeId)){
            return ResultUtil.success();
        }
        throw new ServiceException(ResultCode.ORDER_NOT_EXIST);
    }
}
