package cn.lili.modules.wechat.serviceimpl;

import cn.hutool.core.thread.ThreadUtil;
import cn.lili.common.utils.BeanUtil;
import cn.lili.modules.wechat.entity.dos.WxSubscribe;
import cn.lili.modules.wechat.entity.enums.SubscribeStatusEnums;
import cn.lili.modules.wechat.mapper.WxSubscribeMapper;
import cn.lili.modules.wechat.service.WxSubscribeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: ftyy
 * @description: 微信公众号关注业务实现层
 */
@Service
public class WxSubscribeServiceImpl extends ServiceImpl<WxSubscribeMapper, WxSubscribe> implements WxSubscribeService {


    private static AtomicLong total = new AtomicLong();

    private static AtomicLong countNumber = new AtomicLong();

    @Autowired
    private WxMpService wxMpService;

    @Override
    public void init() {
        //已添加关注人员信息
        List<String> collect = this.list().stream().map(WxSubscribe::getId).collect(Collectors.toList());

        //异步同步微信关注人员信息
        ThreadUtil.execAsync(()->{
            this.recursionWxSubscribe(null,collect);
        });
    }

    /**
     * 同步微信关注者
     * @param openId
     */
    public void recursionWxSubscribe(String openId,List<String> wxSubscribeList) {
        try {
            //微信公众号关注人员--最大为一万条
            WxMpUserList wxMpUserList = wxMpService.getUserService().userList(openId);
            if (wxMpUserList != null && !wxMpUserList.getOpenids().isEmpty() && wxMpUserList.getOpenids().size() > 0) {
                if (total.get() == 0) {
                    //记录总条数
                    total.addAndGet(wxMpUserList.getTotal());
                }
                //记录查询数量
                countNumber.addAndGet(wxMpUserList.getCount());
                List<WxSubscribe> list = new ArrayList<>();
                //遍历关注者且过滤 已存在用户
                wxMpUserList.getOpenids().parallelStream()
                        .filter(a -> wxSubscribeList.stream().noneMatch(b -> !b.equals(a))).forEach(id->{

                            WxSubscribe wxSubscribe = WxSubscribe.builder().id(id)
                                    .subscribeStatus(SubscribeStatusEnums.ALREADY_SUBSCRIBE.name()).build();
                            //获取用户基本信息(UnionID机制)
                            WxMpUser user = null;
                            try {
                                user = wxMpService.getUserService().userInfo(id, "zh_CN");
                            } catch (WxErrorException e) {
                                e.printStackTrace();
                            }
                            if(user!=null){
                                BeanUtil.copyProperties(user,wxSubscribe);
                            }
                            list.add(wxSubscribe);
                        });

                //批量添加或者修改
                this.saveOrUpdateBatch(list);
                //已查询的总数量小于总条数,则继续查询数据
                if (countNumber.get() < total.get()) {
                    recursionWxSubscribe(wxMpUserList.getNextOpenid(),wxSubscribeList);
                }
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
