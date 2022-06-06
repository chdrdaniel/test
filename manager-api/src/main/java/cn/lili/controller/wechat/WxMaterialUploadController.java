package cn.lili.controller.wechat;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.wechat.entity.dos.WxMaterialUpload;
import cn.lili.modules.wechat.entity.dto.MaterialUploadParams;
import cn.lili.modules.wechat.service.WxMaterialUploadService;
import cn.lili.mybatis.util.PageUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author: ftyy
 * @description: 微信公众号永久素材管理
 */
@Api(tags = "微信公众号永久素材")
@RestController
@RequestMapping("/manager/wechat/materialUpload")
public class WxMaterialUploadController {

    @Autowired
    private WxMaterialUploadService wxMaterialUploadService;


    @PostMapping(value = "/saveMaterialPicture")
    @ApiOperation(value = "新增微信公众号永久素材--图片")
    public ResultMessage<WxMaterialUpload> saveMaterialPicture(File file,String fileName, String mediaType) {
        wxMaterialUploadService.saveMaterialPicture(file,fileName,mediaType);
        return ResultUtil.success();
    }

    @GetMapping(value = "/getByPageList")
    @ApiOperation(value = "分页获取微信公众号永久素材列表")
    public ResultMessage<IPage<WxMaterialUpload>> getByPageList(MaterialUploadParams materialUploadParams) {
        return ResultUtil.data(wxMaterialUploadService.page(PageUtil.initPage(materialUploadParams), materialUploadParams.queryWrapper()));
    }

}
