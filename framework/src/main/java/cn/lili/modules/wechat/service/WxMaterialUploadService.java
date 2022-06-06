package cn.lili.modules.wechat.service;


import cn.lili.modules.wechat.entity.dos.WxMaterialUpload;
import com.baomidou.mybatisplus.extension.service.IService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNewsBatchGetResult;

import java.io.File;
import java.util.List;

/**
 * @author: ftyy
 * @description: 微信公众号素材业务层
 */
public interface WxMaterialUploadService extends IService<WxMaterialUpload> {

    /**
     * 新增微信素材--图片
     * @param file
     * @param fileName
     * @param mediaType
     */
    void saveMaterialPicture(File file,String fileName, String mediaType);

    /**
     * 批量添加素材
     * @param items
     */
    void saveList(List<WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem> items);
}
