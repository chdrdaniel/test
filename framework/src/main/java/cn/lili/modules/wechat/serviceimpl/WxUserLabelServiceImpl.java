package cn.lili.modules.wechat.serviceimpl;

import cn.lili.common.utils.StringUtils;
import cn.lili.modules.wechat.entity.dos.WxSubscribe;
import cn.lili.modules.wechat.entity.dos.WxUserLabel;
import cn.lili.modules.wechat.mapper.WxUserLabelMapper;
import cn.lili.modules.wechat.service.WxSubscribeService;
import cn.lili.modules.wechat.service.WxUserLabelService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ftyy
 * @description: 微信公众号用户标签业务实现层
 */
@Service
public class WxUserLabelServiceImpl extends ServiceImpl<WxUserLabelMapper, WxUserLabel> implements WxUserLabelService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxSubscribeService wxSubscribeService;

    @Override
    public void delAllById(String id) {
        try {
            //同步删除微信公众号用户标签
            Boolean flag = wxMpService.getUserTagService().tagDelete(Long.valueOf(id));
            if (flag) {
                this.removeById(id);
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveWxUserLabel(WxUserLabel wxUserLabel) {
        try {
            //同步添加微信公众号用户标签
            WxUserTag wxUserTag = this.wxMpService.getUserTagService().tagCreate(wxUserLabel.getName());
            wxUserLabel.setId(String.valueOf(wxUserTag.getId()));
            this.save(wxUserLabel);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateWxUserLabel(WxUserLabel wxUserLabel) {
        try {
            Boolean flag = this.wxMpService.getUserTagService().tagUpdate(Long.valueOf(wxUserLabel.getId()), wxUserLabel.getName());
            if (flag) {
                this.updateById(wxUserLabel);
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserLabel(String wxUserId, String ids) {
        String[] openIds = new String[1];
        openIds[0] = wxUserId;
        try {
            boolean flag = wxMpService.getUserTagService().batchTagging(Long.valueOf(ids), openIds);
            if (flag) {
                this.updateWxUserLabel(wxUserId,ids);
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserLabel(String wxUserId, String ids) {
        String[] openIds = new String[1];
        openIds[0] = wxUserId;
        //获取用户标签
        WxSubscribe wxSubscribe = wxSubscribeService.getOne(new LambdaQueryWrapper<WxSubscribe>().eq(WxSubscribe::getId, wxUserId));
        //分割用户标签属性
        String[] tagId = wxSubscribe.getTagid_list().split(",");
        String removeId = null;
        //遍历用户标签，找到要移除标签
        for (String id : tagId) {
            //找到要移除标签
            if (!ids.contains(id)) {
                removeId = id;
            }
        }
        try {
            if (StringUtils.isNotEmpty(removeId)) {
                boolean flag = wxMpService.getUserTagService().batchUntagging(Long.valueOf(ids), openIds);
                if (flag) {
                    this.updateWxUserLabel(wxUserId,ids);
                }
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改用户标签
     * @param wxUserId
     * @param ids
     */
    public void updateWxUserLabel(String wxUserId, String ids){
        //获取用户标签
        List<WxUserLabel> list = this.list(new LambdaQueryWrapper<WxUserLabel>().in(WxUserLabel::getId, ids));
        String tagIdNameList ="";
        //校验用户标签名称不为空
        if(!list.isEmpty()  &&  list.size()>0){
            for (WxUserLabel wxUserLabel : list) {
                tagIdNameList+=wxUserLabel.getName();
            }
            //修改用户标签
            wxSubscribeService
                    .update(new LambdaUpdateWrapper<WxSubscribe>()
                            .eq(WxSubscribe::getId,wxUserId)
                            .set(WxSubscribe::getTagIdNameList,tagIdNameList)
                            .set(WxSubscribe::getTagid_list, ids));
        }
    }

}
