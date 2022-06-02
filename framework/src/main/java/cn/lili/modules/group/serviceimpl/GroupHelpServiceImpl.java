package cn.lili.modules.group.serviceimpl;

import cn.hutool.json.JSONUtil;
import cn.lili.common.enums.ResultCode;
import cn.lili.common.exception.ServiceException;
import cn.lili.common.properties.RocketmqCustomProperties;
import cn.lili.common.security.AuthUser;
import cn.lili.common.security.context.UserContext;
import cn.lili.modules.group.entity.dos.GroupHelp;
import cn.lili.modules.group.entity.dto.GroupHelpMessage;
import cn.lili.modules.group.entity.dto.GroupHelpParams;
import cn.lili.modules.group.entity.enums.GroupHelpStatusEnums;
import cn.lili.modules.group.mapper.GroupHelpMapper;
import cn.lili.modules.group.service.GroupHelpService;
import cn.lili.modules.store.service.StoreService;
import cn.lili.mybatis.util.PageUtil;
import cn.lili.rocketmq.RocketmqSendCallbackBuilder;
import cn.lili.rocketmq.tags.MemberTagsEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 帮卖会员业务层实现
 *
 * @author chc
 * @since 2022-5-20 16:15:05
 */
@Service
public class GroupHelpServiceImpl extends ServiceImpl<GroupHelpMapper, GroupHelp> implements GroupHelpService {
    /**
     * 店铺
     */
    @Autowired
    private StoreService storeService;

    /**
     * rocketMq
     */
    @Autowired
    private RocketMQTemplate rocketmqTemplate;
    /**
     * rocketMq配置
     */
    @Autowired
    private RocketmqCustomProperties rocketmqCustomProperties;

    @Override
    public boolean apply(String storeId) {
        AuthUser currentUser = UserContext.getCurrentUser();
        GroupHelp groupHelp =new GroupHelp(currentUser);
        //检查该用户是否在该店铺下申请过
        GroupHelp checkUser = this.getOne(new LambdaQueryWrapper<GroupHelp>().eq(GroupHelp::getStoreId, storeId).eq(GroupHelp::getMemberId, currentUser.getId()));
        if(Optional.ofNullable(checkUser).isPresent()){
            //如果改会员在该店铺下状态为已申请,则提示用户等待
            if(checkUser.getStatus().equals(GroupHelpStatusEnums.APPLY.name())){
                throw new ServiceException(ResultCode.GROUP_HELP_IS_APPLY_ERROR);
            }else if(checkUser.getStatus().equals(GroupHelpStatusEnums.REFUSE.name())){
                //如果该用户审核被拒绝,重新申请
                checkUser.setStatus(GroupHelpStatusEnums.APPLY.name());
                return this.updateById(checkUser);
            }
        }
        String storeName = storeService.getById(storeId).getStoreName();
        groupHelp.setStoreId(storeId);
        groupHelp.setStoreName(storeName);
        groupHelp.setStatus(GroupHelpStatusEnums.APPLY.name());
        return this.save(groupHelp);
    }

    @Override
    public boolean auth(String groupHelpId, GroupHelpStatusEnums groupHelpStatusEnums) {
        return this.update(new LambdaUpdateWrapper<GroupHelp>().eq(GroupHelp::getId,groupHelpId).set(GroupHelp::getStatus,groupHelpStatusEnums.name()));
    }



    @Override
    public IPage<GroupHelp> groupHelpPage(GroupHelpParams groupHelpParams) {
        groupHelpParams.setStoreId(UserContext.getCurrentUser().getStoreId());
        return this.page(PageUtil.initPage(groupHelpParams), groupHelpParams.queryWrapper());
    }

    @Override
    public boolean retreatGroupHeads(String groupHedsId) {
        return this.update(new LambdaUpdateWrapper<GroupHelp>().eq(GroupHelp::getId,groupHedsId).set(GroupHelp::getStatus, GroupHelpStatusEnums.RETREAT.name()));
    }

    @Override
    public boolean resumeGroupHeads(String groupHedsId) {
        return this.update(new LambdaUpdateWrapper<GroupHelp>().eq(GroupHelp::getId,groupHedsId).set(GroupHelp::getStatus, GroupHelpStatusEnums.PASS.name()));
    }

    /**
     * 审核状态发生改变
     */
    private void releaseStatusMessage(GroupHelp groupHelp) {
        GroupHelpMessage message=new GroupHelpMessage(groupHelp);
        sendReleaseUpdateStatusMessage(message);
    }

    /**
     * 发送MQ消息
     *
     * @param message
     */
    public void sendReleaseUpdateStatusMessage(GroupHelpMessage message) {
        String destination = rocketmqCustomProperties.getMemberTopic() + ":" + MemberTagsEnum.MEMBER_APPLY_GROUP_HELP_STATUS_CHANGE.name();
        //发送文章状态变更消息
        rocketmqTemplate.asyncSend(destination, JSONUtil.toJsonStr(message), RocketmqSendCallbackBuilder.commonCallback());
    }
}
