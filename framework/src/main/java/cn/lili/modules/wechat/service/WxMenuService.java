package cn.lili.modules.wechat.service;

import cn.lili.modules.wechat.entity.dos.WechatMenu;
import cn.lili.modules.wechat.entity.vo.WechatMenuVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author: ftyy
 * @description: 微信公众号菜单业务层
 */
public interface WxMenuService extends IService<WechatMenu> {

    /**
     * 获取微信公众号菜单
     * @return
     */
    List<WechatMenuVO> tree();

    /**
     * 初始化微信公众号菜单
     */
    void init();

    /**
     * 新增微信公众号菜单
     * @param wxMenu
     */
    void saveWxMenu(WechatMenu wxMenu);

    /**
     * 修改微信公众号菜单
     * @param wxMenu
     */
    void updateWechatMenu(WechatMenu wxMenu);

    /**
     * 删除微信公众号菜单
     * @param id
     */
    void delAllByIds(String id);
}
