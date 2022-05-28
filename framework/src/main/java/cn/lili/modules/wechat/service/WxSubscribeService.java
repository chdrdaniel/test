package cn.lili.modules.wechat.service;

import cn.lili.modules.wechat.entity.dos.WxSubscribe;
import cn.lili.modules.wechat.entity.dto.WxSubscribeParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author: ftyy
 * @description: 微信公众号关注业务层
 */
public interface WxSubscribeService extends IService<WxSubscribe> {
    /**
     * 初始化关注微信公众号人员
     */
    void init();

    /**
     * 分页获取微信公众号关注列表
     * @param wxSubscribeParams
     * @return
     */
    IPage<WxSubscribe> getByPage(WxSubscribeParams wxSubscribeParams);
}
