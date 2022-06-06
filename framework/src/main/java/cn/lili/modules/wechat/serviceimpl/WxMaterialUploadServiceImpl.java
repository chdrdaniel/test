package cn.lili.modules.wechat.serviceimpl;

import cn.lili.common.utils.BeanUtil;
import cn.lili.modules.wechat.entity.dos.WxMaterialUpload;
import cn.lili.modules.wechat.entity.enums.MaterialUploadEnums;
import cn.lili.modules.wechat.mapper.WxMaterialUploadMapper;
import cn.lili.modules.wechat.service.WxMaterialUploadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNewsBatchGetResult;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ftyy
 * @description: 微信公众号素材业务实现层
 */
@Service
public class WxMaterialUploadServiceImpl extends ServiceImpl<WxMaterialUploadMapper, WxMaterialUpload> implements WxMaterialUploadService {


    @Autowired
    private WxMpService wxMpService;


    @Override
    public void saveMaterialPicture(File file,String fileName, String mediaType) {
        WxMpMaterial wxMaterial = new WxMpMaterial();
        wxMaterial.setFile(file);
        wxMaterial.setName(fileName);
        try {
            WxMpMaterialUploadResult wxMpMaterialUploadResult = this.wxMpService.getMaterialService().materialFileUpload(mediaType, wxMaterial);
            WxMaterialUpload wxMaterialUpload = new WxMaterialUpload();
            wxMaterialUpload.setMedia_id(wxMpMaterialUploadResult.getMediaId());
            wxMaterialUpload.setMaterialUploadType(mediaType);
            wxMaterialUpload.setName(fileName);
            this.save(wxMaterialUpload);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveList(List<WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem> items) {
        List<WxMaterialUpload> list = new ArrayList<>();
        for (WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem item : items) {
            WxMaterialUpload wxMaterialUpload = new WxMaterialUpload();
            BeanUtil.copyProperties(item,wxMaterialUpload);
            wxMaterialUpload.setMaterialUploadType(MaterialUploadEnums.WX_MATERIAL_UPLOAD_NEWS.description());
            list.add(wxMaterialUpload);
        }
        this.saveOrUpdateBatch(list);
    }
}
