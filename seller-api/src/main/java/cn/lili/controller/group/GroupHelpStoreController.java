package cn.lili.controller.group;

import cn.lili.common.enums.ResultCode;
import cn.lili.common.enums.ResultUtil;
import cn.lili.common.exception.ServiceException;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.group.entity.dos.GroupHelp;
import cn.lili.modules.group.entity.dto.GroupHelpParams;
import cn.lili.modules.group.entity.enums.GroupHelpStatusEnums;
import cn.lili.modules.group.service.GroupHelpService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商家端,帮卖会员Api
 *
 * @Author chc
 * @since 2022/5/20 16:48
 */
@Api(tags = "商家端,帮卖会员Api")
@RestController
@RequestMapping("store/group/help")
public class GroupHelpStoreController {
    /**
     * 帮卖会员
     */
    @Autowired
    private GroupHelpService groupHelpService;

    @ApiOperation("通过状态获取帮卖会员分页")
    @GetMapping()
    public ResultMessage<IPage<GroupHelp>> page(GroupHelpParams groupHelpParams) {
        return ResultUtil.data(groupHelpService.groupHelpPage(groupHelpParams));
    }

    @ApiOperation("审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupHelpId", value = "帮卖Id", required = true, paramType = "path"),
            @ApiImplicitParam(name = "status", value = "审核状态", required = true, paramType = "query")
    })
    @PutMapping("/auth/{groupHelpId}")
    public ResultMessage<Object> auth(@PathVariable("groupHelpId") String groupHelpId,String status) {
        if(groupHelpService.auth(groupHelpId, GroupHelpStatusEnums.valueOf(status))){
            return ResultUtil.success();
        }
        throw new ServiceException(ResultCode.GROUP_HELP_APPLY_ERROR);
    }

    @ApiOperation("清退清退会员")
    @ApiImplicitParam(name = "groupHelpId", value = "帮卖Id", required = true, paramType = "path")
    @PutMapping("/retreat/{groupHelpId}")
    public ResultMessage<Object> retreat(@PathVariable("groupHelpId") String groupHelpId){
        if(groupHelpService.retreatGroupHeads(groupHelpId)){
            return ResultUtil.success();
        }
        throw new ServiceException(ResultCode.GROUP_HELP_RETREAT_ERROR);
    }

    @ApiOperation("恢复帮卖会员")
    @ApiImplicitParam(name = "groupHelpId", value = "帮卖Id", required = true, paramType = "path")
    @PutMapping("/resume/{groupHelpId}")
    public ResultMessage<Object> resume(@PathVariable("groupHelpId") String groupHelpId){
        if(groupHelpService.resumeGroupHeads(groupHelpId)){
            return ResultUtil.success();
        }
        throw new ServiceException(ResultCode.GROUP_HELP_RESUME_ERROR);
    }
}
