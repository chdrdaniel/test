package cn.lili.controller.group;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.group.entity.dos.GroupGoods;
import cn.lili.modules.group.entity.vos.GroupGoodsVO;
import cn.lili.modules.group.service.GroupGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 买家端,团购商品Api
 *
 * @Author chc
 * @since 2022/5/23 9:21
 */
@Api(tags = "买家端,团购商品Api")
@RestController
@RequestMapping("/buyer/group/goods")
public class GroupGoodsBuyerController {

    @Autowired
    private GroupGoodsService groupGoodsService;

    @ApiOperation("查看团购商品信息")
    @ApiImplicitParam(name = "groupGoodsId", value = "商品Id", required = true, paramType = "path")
    @GetMapping("info/{groupGoodsId}")
    public ResultMessage<GroupGoodsVO> groupGoodsInfo(@PathVariable("groupGoodsId") String groupGoodsId){
        return ResultUtil.data(groupGoodsService.info(groupGoodsId));
    }
}
