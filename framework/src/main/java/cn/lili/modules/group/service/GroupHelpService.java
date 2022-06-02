package cn.lili.modules.group.service;

import cn.lili.modules.group.entity.dos.GroupHelp;
import cn.lili.modules.group.entity.dto.GroupHelpParams;
import cn.lili.modules.group.entity.enums.GroupHelpStatusEnums;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 帮卖会员业务层
 *
 * @author chc
 * @since 2022-5-20 16:14:31
 */
public interface GroupHelpService extends IService<GroupHelp> {
    /**
     * 申请团长
     *
     * @return 申请结果
     */
    boolean apply(String storeId);

    /**
     * 审核团长
     *
     * @param groupHelpId 团长Id
     * @return 审核结果
     */
    boolean auth(String groupHelpId, GroupHelpStatusEnums groupHelpStatusEnums);

    /**
     * 获取团长分页
     * @param groupHelpParams 查询条件
     * @return 团长分页
     */
    IPage<GroupHelp> groupHelpPage(GroupHelpParams groupHelpParams);


    /**
     * 清退团长
     *
     * @param groupHedsId 团长Id
     * @return 清退结果
     */
    boolean retreatGroupHeads(String groupHedsId);

    /**
     * 恢复团长
     *
     * @param groupHedsId 团长Id
     * @return 恢复结果
     */
    boolean resumeGroupHeads(String groupHedsId);
}
