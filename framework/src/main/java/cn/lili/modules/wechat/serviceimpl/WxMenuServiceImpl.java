package cn.lili.modules.wechat.serviceimpl;

import cn.lili.modules.wechat.entity.dos.WechatMenu;
import cn.lili.modules.wechat.entity.enums.WxMenuTypeEnums;
import cn.lili.modules.wechat.entity.vo.WechatMenuVO;
import cn.lili.modules.wechat.mapper.WxMenuMapper;
import cn.lili.modules.wechat.service.WxMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpSelfMenuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author: ftyy
 * @description: 微信公众号菜单业务实现层
 */
@Service
public class WxMenuServiceImpl extends ServiceImpl<WxMenuMapper, WechatMenu> implements WxMenuService {


    /**
     * 微信公众号appid
     */
    @Value("${wx.mp.appId}")
    private String appId;

    @Autowired
    private WxMpService wxService;


    @Override
    public List<WechatMenuVO> tree() {
        try {
            List<WechatMenu> menus = this.list();
            return tree(menus);
        } catch (Exception e) {
            log.error("微信菜单树错误", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void init() {
        try {
            this.remove(new LambdaQueryWrapper<>());
            //获取微信公众号菜单
            WxMpGetSelfMenuInfoResult selfMenuInfo = this.wxService.switchoverTo(appId).getMenuService().getSelfMenuInfo();
            //数据不为空
            if (selfMenuInfo != null && selfMenuInfo.getSelfMenuInfo() != null) {
                this.recursionWxMenu(selfMenuInfo.getSelfMenuInfo().getButtons(), "0");
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化添加微信菜单
     *
     * @param wxMpSelfMenuButton
     * @param parentId
     */
    public void recursionWxMenu(List<WxMpSelfMenuInfo.WxMpSelfMenuButton> wxMpSelfMenuButton, String parentId) {

        if (!wxMpSelfMenuButton.isEmpty() && wxMpSelfMenuButton.size() > 0) {
            //遍历
            wxMpSelfMenuButton.forEach(a -> {
                //添加一级菜单
                WechatMenu wxMenu = new WechatMenu(a);
                //父级菜单id
                wxMenu.setParentId(parentId);
                wxMenu.setType(WxMenuTypeEnums.wxMenuNameOf(a.getType()));
                this.save(wxMenu);
                //子级菜单不为空
                if (a.getSubButtons() != null && !a.getSubButtons().getSubButtons().isEmpty() && a.getSubButtons().getSubButtons().size() > 0) {
                    recursionWxMenu(a.getSubButtons().getSubButtons(), wxMenu.getId());
                }
            });
        }
    }

    @Override
    public void saveWxMenu(WechatMenu wechatMenu) {
        //新增微信公众号菜单
        this.save(wechatMenu);
        //更新微信菜单
        this.wxMenuTree();
    }

    @Override
    public void updateWechatMenu(WechatMenu wxMenu) {
        //修改微信公众号菜单
        this.updateById(wxMenu);
        //更新微信菜单
        this.wxMenuTree();
    }

    @Override
    public void delAllByIds(String id) {
        //删除微信公众号菜单
        this.removeById(id);
        //更新微信菜单
        this.wxMenuTree();
    }

    /**
     * 拼接微信菜单及更新
     *
     * @return
     */
    private void wxMenuTree() {
        //获取菜单
        List<WechatMenu> menus = this.list();
        //菜单列表不为空
        if (menus.isEmpty() && menus.size() > 0) {
            WxMenu menu = new WxMenu();
            //遍历菜单列表
            for (WechatMenu wxMenu : menus) {
                //获取一级菜单
                if (wxMenu.getParentId().equals("0")) {
                    //获取菜单按钮类型
                    WxMenuButton wxMenuButton = getWxMenuButton(wxMenu);
                    //按钮不为空
                    if (wxMenuButton != null) {
                        //获取子级及下级按钮菜单
                        wxMenuInitChild(menus, wxMenuButton, wxMenu);
                    }
                    //添加微信菜单
                    menu.getButtons().add(wxMenuButton);
                }
            }
            //添加微信菜单
            Optional.ofNullable(menu).ifPresent(a -> {
                try {
                    //微信自定义菜单删除
                    this.wxService.switchoverTo(appId).getMenuService().menuDelete();
                    this.wxService.switchover(appId);
                    //微信自定义菜单增加
                    this.wxService.getMenuService().menuCreate(menu);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    /**
     * 拼接微信子级菜单
     *
     * @param menus
     * @param wxMenuButton
     * @param wxMenu
     */
    public void wxMenuInitChild(List<WechatMenu> menus, WxMenuButton wxMenuButton, WechatMenu wxMenu) {
        //获取子级菜单列表
        menus.stream().filter(menu -> menu.getParentId().equals(wxMenu.getId())).forEach(childMenu -> {
            //获取菜单按钮类型
            WxMenuButton menuButton = getWxMenuButton(childMenu);
            //添加子级按钮菜单
            wxMenuButton.getSubButtons().add(menuButton);
            //获取子级菜单
            wxMenuInitChild(menus, menuButton, childMenu);
        });

    }


    /**
     * 微信按钮类型
     * @param wxMenu
     * @return
     */
    public WxMenuButton getWxMenuButton(WechatMenu wxMenu) {
        WxMenuButton button = new WxMenuButton();
        button.setName(wxMenu.getName());
        button.setKey(wxMenu.getWxKey());
        switch (wxMenu.getType()) {
            //点击按钮
            case WX_MENU_CLICK: {
                button.setType(WxMenuTypeEnums.WX_MENU_CLICK.description());
                return button;
            }
            //微信扫一扫按钮
            case WX_MENU_SCANCODE_PUSH: {
                button.setType(WxMenuTypeEnums.WX_MENU_SCANCODE_PUSH.description());
                return button;
            }
            //扫码推事件且弹出
            case WX_MENU_SCANCODE_WAITMSG: {
                button.setType(WxMenuTypeEnums.WX_MENU_SCANCODE_WAITMSG.description());
                return button;
            }
            //弹出系统拍照发图用户点击按钮
            case WX_MENU_PIC_SYSPHOTO: {
                button.setType(WxMenuTypeEnums.WX_MENU_PIC_SYSPHOTO.description());
                return button;
            }
            //弹出微信相册发图器用户点击按钮后
            case WX_MENU_PIC_PHOTO_OR_ALBUM: {
                button.setType(WxMenuTypeEnums.WX_MENU_PIC_PHOTO_OR_ALBUM.description());
                return button;
            }
            //扫码推事件且弹出
            case WX_MENU_PIC_WEIXIN: {
                button.setType(WxMenuTypeEnums.WX_MENU_PIC_WEIXIN.description());
                return button;
            }
            //弹出地理位置选择器用户点击按钮后
            case WX_MENU_LOCATION_SELECT: {
                button.setType(WxMenuTypeEnums.WX_MENU_LOCATION_SELECT.description());
                return button;
            }
            //下发消息（除文本消息）用户点击media_id类型按钮
            case WX_MENU_MEDIA_ID: {
                button.setType(WxMenuTypeEnums.WX_MENU_MEDIA_ID.description());
                return button;
            }
            //用户点击 article_id 类型按钮后，微信客户端将会以卡片形式，下发开发者在按钮中填写的图文消息
            case WX_MENU_ARTICLE_ID: {
                button.setType(WxMenuTypeEnums.WX_MENU_ARTICLE_ID.description());
                return button;
            }
            //类似 view_limited，但不使用 media_id 而使用 article_id
            case WX_MENU_ARTICLE_VIEW_LIMITED: {
                button.setType(WxMenuTypeEnums.WX_MENU_ARTICLE_VIEW_LIMITED.description());
                return button;
            }
            default: {
                return null;
            }
        }
    }


    /**
     * 传入自定义微信菜单集合
     *
     * @param menus 自定义菜单集合
     * @return 修改后的自定义菜单集合
     */
    private List<WechatMenuVO> tree(List<WechatMenu> menus) {
        List<WechatMenuVO> tree = new ArrayList<>();
        menus.forEach(item -> {
            //获取一级菜单
            if (item.getParentId().equals("0")) {
                WechatMenuVO treeItem = new WechatMenuVO(item);
                initChild(treeItem, menus);
                tree.add(treeItem);
            }
        });
        return tree;
    }

    /**
     * 递归初始化子树
     *
     * @param tree  树结构
     * @param menus 数据库对象集合
     */
    private void initChild(WechatMenuVO tree, List<WechatMenu> menus) {
        if (menus == null) {
            return;
        }
        menus.stream().filter(item -> (item.getParentId().equals(tree.getId()))).forEach(child -> {
            WechatMenuVO childTree = new WechatMenuVO(child);
            initChild(childTree, menus);
            tree.getChildren().add(childTree);
        });
    }
}