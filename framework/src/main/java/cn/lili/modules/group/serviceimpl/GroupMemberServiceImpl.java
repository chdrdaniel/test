package cn.lili.modules.group.serviceimpl;

import cn.hutool.core.text.CharSequenceUtil;
import cn.lili.common.enums.ResultCode;
import cn.lili.common.exception.ServiceException;
import cn.lili.common.security.AuthUser;
import cn.lili.common.security.context.UserContext;
import cn.lili.common.vo.PageVO;
import cn.lili.modules.group.entity.dos.GroupMember;
import cn.lili.modules.group.mapper.GroupMemberMapper;
import cn.lili.modules.group.service.GroupMemberService;
import cn.lili.mybatis.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 团员业务层实现
 *
 * @author chc
 * @since 2022-5-19 16:55:06
 */
public class GroupMemberServiceImpl extends ServiceImpl<GroupMemberMapper, GroupMember> implements GroupMemberService {
    @Override
    public IPage<GroupMember> queryPage(PageVO pageVO, String memberName) {
        return this.page(PageUtil.initPage(pageVO),new LambdaQueryWrapper<GroupMember>().like(CharSequenceUtil.isNotEmpty(memberName),GroupMember::getMemberName,memberName));
    }

    @Override
    public GroupMember add(String headId,Double consumptionAmount) {
        //获取当前登录用户
        AuthUser currentUser = UserContext.getCurrentUser();
        if(currentUser!=null){
            //组织数据
            GroupMember groupMember=new GroupMember(currentUser);
            groupMember.setHeadId(headId);
            groupMember.setConsumptionAmount(consumptionAmount);
            this.save(groupMember);
            return groupMember;
        }
        //如果用户为空抛出异常
        throw new ServiceException(ResultCode.USER_NOT_LOGIN);
    }
}
