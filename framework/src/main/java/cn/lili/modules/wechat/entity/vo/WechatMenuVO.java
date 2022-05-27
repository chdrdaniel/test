package cn.lili.modules.wechat.entity.vo;

import cn.lili.common.utils.BeanUtil;
import cn.lili.modules.wechat.entity.dos.WechatMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ftyy
 * @description: 描述
 */
@Data
@NoArgsConstructor
public class WechatMenuVO extends WechatMenu {

    @ApiModelProperty(value = "子菜单")
    private List<WechatMenuVO> children = new ArrayList<>();

    public WechatMenuVO(WechatMenu wxMenu){
        BeanUtil.copyProperties(wxMenu,this);
    }
}
