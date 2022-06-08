package cn.lili.modules.member.serviceimpl;


import cn.lili.common.enums.ResultCode;
import cn.lili.common.exception.ServiceException;
import cn.lili.common.security.context.UserContext;
import cn.lili.common.utils.BeanUtil;
import cn.lili.common.vo.PageVO;
import cn.lili.modules.member.entity.dos.Member;
import cn.lili.modules.member.entity.dos.MemberReceipt;
import cn.lili.modules.member.entity.vo.MemberReceiptAddVO;
import cn.lili.modules.member.entity.vo.MemberReceiptVO;
import cn.lili.modules.member.mapper.MemberReceiptMapper;
import cn.lili.modules.member.service.MemberReceiptService;
import cn.lili.modules.member.service.MemberService;
import cn.lili.modules.order.order.entity.vo.ReceiptVO;
import cn.lili.mybatis.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 会员发票业务层实现
 *
 * @author Chopper
 * @since 2021-03-29 14:10:16
 */
@Service
public class MemberReceiptServiceImpl extends ServiceImpl<MemberReceiptMapper, MemberReceipt> implements MemberReceiptService {
    @Autowired
    private MemberService memberService;

    @Override
    public IPage<MemberReceipt> getPage(MemberReceiptVO memberReceiptVO, PageVO pageVO) {
        return this.page(PageUtil.initPage(pageVO), memberReceiptVO.lambdaQueryWrapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addMemberReceipt(ReceiptVO receiptVO, String memberId) {
        //校验发票抬头是否重复
        List<MemberReceipt> receipts = this.baseMapper.selectList(new QueryWrapper<MemberReceipt>()
                .eq("member_id", memberId)
                .eq("receipt_type", receiptVO.getReceiptType())
                .eq("company_name", receiptVO.getCompanyName())
                .eq("taxpayer_id", receiptVO.getTaxpayerId())
        );
        if (receipts.size() > 0) {
            throw new ServiceException(ResultCode.USER_RECEIPT_REPEAT_ERROR);
        }
        //参数封装
        MemberReceipt memberReceipt = new MemberReceipt();
        BeanUtil.copyProperties(receiptVO, memberReceipt);
        //根据会员信息查询会员
        Member member = memberService.getById(memberId);
        if (member == null) {
            throw new ServiceException(ResultCode.USER_NOT_EXIST);
        }
        memberReceipt.setMemberId(memberId);
        memberReceipt.setMemberName(member.getUsername());
        //设置发票默认
        List<MemberReceipt> list = this.baseMapper.selectList(new QueryWrapper<MemberReceipt>().eq("member_id", memberId));
        //如果当前会员只有一个发票则默认为默认发票，反之需要校验参数默认值，做一些处理
        if (list.size() > 0) {
            //如果参数传递新添加的发票信息为默认，则需要把其他发票置为非默认
            this.update(new UpdateWrapper<MemberReceipt>()
                    .eq("receipt_type", receiptVO.getReceiptType())
                    .set("is_default", false));
        }
        //设置当前发票信息为默认
        memberReceipt.setIsDefault(true);

        return this.baseMapper.insert(memberReceipt) > 0 ? true : false;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean editMemberReceipt(ReceiptVO receiptVO, String id, String memberId) {
        //根据会员发票信息查询发票
        MemberReceipt receipt = this.getById(id);
        if (receipt == null) {
            throw new ServiceException(ResultCode.USER_RECEIPT_NOT_EXIST);
        }
        //校验发票信息是否重复
        List<MemberReceipt> receipts = this.baseMapper.selectList(new QueryWrapper<MemberReceipt>()
                .eq("member_id", memberId)
                .eq("receipt_type", receiptVO.getReceiptType())
                .eq("company_name", receiptVO.getCompanyName())
                .eq("taxpayer_id", receiptVO.getTaxpayerId())
                .ne("id", id)
        );
        if (receipts.size() > 0) {
            throw new ServiceException(ResultCode.USER_RECEIPT_REPEAT_ERROR);
        }
        BeanUtil.copyProperties(receiptVO, receipt);
        return this.baseMapper.updateById(receipt) > 0 ? true : false;

    }

    @Override
    public Boolean deleteMemberReceipt(String id) {
        //根据会员id查询发票信息
        MemberReceipt memberReceiptDb = this.baseMapper.selectById(id);
        if (memberReceiptDb != null) {
            //如果会员发票信息不为空 则逻辑删除此发票信息
            memberReceiptDb.setDeleteFlag(true);
            this.baseMapper.updateById(memberReceiptDb);
        }
        return true;
    }

    @Override
    public List<MemberReceipt> getMemberReceipt(String receiptType, String memberId) {
        return this.baseMapper.selectList(new QueryWrapper<MemberReceipt>()
                .eq("member_id", memberId)
                .eq("receipt_type", receiptType)
                .eq("delete_flag", false)
                .orderByDesc("create_time"));
    }

    @Override
    public Boolean add(MemberReceipt memberReceipt) {
        //根据类型和税号校验是否存在
        MemberReceipt temp = this.baseMapper.selectOne(new QueryWrapper<MemberReceipt>()
                .eq("receipt_type", memberReceipt.getReceiptType())
                .eq("taxpayer_id", memberReceipt.getTaxpayerId())
                .eq("member_id", memberReceipt.getMemberId()));
        if (temp == null) {
            //如果是默认的
            if (memberReceipt.getIsDefault()) {
                //将此会员所有的发票置为非默认
                LambdaUpdateWrapper<MemberReceipt> updateWrapper = Wrappers.lambdaUpdate();
                updateWrapper.set(MemberReceipt::getIsDefault, false);
                updateWrapper.eq(MemberReceipt::getMemberId, memberReceipt.getMemberId());
                updateWrapper.eq(MemberReceipt::getReceiptType, memberReceipt.getReceiptType());
                this.update(updateWrapper);
            }
            memberReceipt.setCreateTime(new Date());
            return this.baseMapper.insert(memberReceipt) >= 0 ? true : false;
        } else {
            memberReceipt.setId(temp.getId());
            memberReceipt.setIsDefault(memberReceipt.getIsDefault());
            return this.baseMapper.updateById(memberReceipt) >= 0 ? true : false;
        }
    }

    @Override
    public MemberReceipt getDefault(String receiptType) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<MemberReceipt>()
                .eq(MemberReceipt::getReceiptType, receiptType)
                .eq(MemberReceipt::getIsDefault, true)
                .eq(MemberReceipt::getMemberId, UserContext.getCurrentUser().getId()));
    }
}