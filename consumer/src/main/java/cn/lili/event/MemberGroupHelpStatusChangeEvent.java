package cn.lili.event;

import cn.lili.modules.group.entity.dto.GroupHelpMessage;

/**
 * @Author chc
 * @since 2022/5/27 10:38
 */
public interface MemberGroupHelpStatusChangeEvent {

    /**
     * 会员申请团长状态变化
     * @param message 申请团长信息
     */
    void memberGroupHelpStatusChange(GroupHelpMessage message);
}
