package cn.lili.modules.group.service;

import cn.lili.common.vo.PageVO;
import cn.lili.modules.group.entity.dos.GroupMember;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 团员业务层
 *
 * @author chc
 * @since 2022-5-19 16:55:06
 */
public interface GroupMemberService extends IService<GroupMember> {

    /**
     * 获取团员分页
     * @param pageVO 分页
     * @param memberName 团员名称
     * @return 分页
     */
    IPage<GroupMember> queryPage(PageVO pageVO,String memberName);

    /**
     * 增加团员
     * @param headId  团长Id
     * @param consumptionAmount 消费金额
     * @return 团员信息
     */
    GroupMember add(String headId,Double consumptionAmount);

    /**
     * 申请成为帮卖团长
     */
}
