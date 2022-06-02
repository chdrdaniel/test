package cn.lili.modules.wechat.service;


import cn.lili.modules.wechat.entity.dos.WxUserLabel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author: ftyy
 * @description: 微信公众号用户标签业务层
 */
public interface WxUserLabelService extends IService<WxUserLabel> {
    /**
     * 删除微信公众号用户标签
     * @param id
     */
    void delAllById(String id);

    /**
     * 新增微信公众号用户标签
     * @param wxUserLabel
     */
    void saveWxUserLabel(WxUserLabel wxUserLabel);

    /**
     * 修改微信公众号用户标签
     * @param wxUserLabel
     */
    void updateWxUserLabel(WxUserLabel wxUserLabel);

    /**
     * 微信用户增加标签
     * @param wxUserId
     * @param ids
     */
    void updateUserLabel(String wxUserId, String ids);

    /**
     * 微信用户删除标签
     * @param wxUserId
     * @param ids
     */
    void deleteUserLabel(String wxUserId, String ids);
}
