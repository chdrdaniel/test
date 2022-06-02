package cn.lili.controller.group;

import cn.lili.common.enums.ResultCode;
import cn.lili.common.enums.ResultUtil;
import cn.lili.common.exception.ServiceException;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.group.entity.dos.GroupGoods;
import cn.lili.modules.group.entity.dto.GroupGoodsParams;
import cn.lili.modules.group.entity.vos.GroupGoodsVO;
import cn.lili.modules.group.service.GroupGoodsService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商家端,团购商品池Api
 *
 * @Author chc
 * @since 2022/5/23 9:02
 */
@Api(tags = "商家端,团购商品池Api")
@RestController
@RequestMapping
public class GroupGoodsStoreController {

    /**
     * 团购商品
     */
    @Autowired
    private GroupGoodsService groupGoodsService;

    @ApiOperation("添加商品")
    @PostMapping("/addGroupGoods")
    public ResultMessage<GroupGoods> addGroupGoods(@RequestBody GroupGoods groupGoods){
        return ResultUtil.data(groupGoodsService.add(groupGoods));
    }

    @ApiOperation("获取商品分页")
    @GetMapping()
    public ResultMessage<IPage<GroupGoods>> groupGoodsPage(GroupGoodsParams groupGoodsParams){
        return ResultUtil.data(groupGoodsService.queryPage(groupGoodsParams));
    }

    @ApiOperation("查看团购商品信息")
    @ApiImplicitParam(name = "groupGoodsId", value = "商品Id", required = true, paramType = "path")
    @GetMapping("info/{groupGoodsId}")
    public ResultMessage<GroupGoodsVO> groupGoodsInfo(@PathVariable("groupGoodsId") String groupGoodsId){
        return ResultUtil.data(groupGoodsService.info(groupGoodsId));
    }

    @ApiOperation("删除商品")
    @ApiImplicitParam(name = "groupGoodsId", value = "商品Id", required = true, paramType = "path")
    @DeleteMapping("/remove/{groupGoodsId}")
    public ResultMessage<Object> removeGroupGoods(@PathVariable("groupGoodsId") String groupGoodsId){
        if(groupGoodsService.removeGroupGoods(groupGoodsId)){
            return ResultUtil.success();
        }
        throw new ServiceException(ResultCode.GROUP_GOODS_REMOVE_ERROR);
    }

    @ApiOperation("修改商品")
    @ApiImplicitParam(name = "groupGoodsId", value = "商品Id", required = true, paramType = "path")
    @PutMapping("/update/{groupGoodsId}")
    public ResultMessage<Object> updateGroupGoods(@PathVariable("groupGoodsId") String groupGoodsId,@RequestBody GroupGoods groupGoods){
        groupGoods.setId(groupGoodsId);
        if(groupGoodsService.updateGroupGoods(groupGoods)){
            return ResultUtil.success();
        }
        throw new ServiceException(ResultCode.GROUP_GOODS_UPDATE_ERROR);
    }
}
