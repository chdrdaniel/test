package cn.lili.modules.member.service;


import cn.lili.common.vo.PageVO;
import cn.lili.modules.member.entity.dos.MemberReceipt;
import cn.lili.modules.member.entity.vo.MemberReceiptAddVO;
import cn.lili.modules.member.entity.vo.MemberReceiptVO;
import cn.lili.modules.order.order.entity.vo.ReceiptVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 会员发票业务层
 *
 * @author Chopper
 * @since 2021-03-29 14:10:16
 */
public interface MemberReceiptService extends IService<MemberReceipt> {

    /**
     * 查询会员发票列表
     *
     * @param memberReceiptVO 会员发票信息
     * @param pageVO          分页信息
     * @return 会员发票分页
     */
    IPage<MemberReceipt> getPage(MemberReceiptVO memberReceiptVO, PageVO pageVO);

    /**
     * 添加会员发票信息
     *
     * @param memberReceiptAddVO 会员发票信息
     * @param memberId           会员ID
     * @return 操作状态
     */
    Boolean addMemberReceipt(MemberReceiptAddVO memberReceiptAddVO, String memberId);

    /**
     * 修改会员发票信息
     *
     * @param memberReceiptAddVO 会员发票信息
     * @param memberId           会员ID
     * @return 操作状态
     */
    Boolean editMemberReceipt(MemberReceiptAddVO memberReceiptAddVO, String memberId);

    /**
     * 删除会员发票信息
     *
     * @param id 发票ID
     * @return 操作状态
     */
    Boolean deleteMemberReceipt(String id);

    /**
     * 根据纳税人识别号和发票类型查询会员发票
     *
     * @param receiptType 发票类型
     * @return
     */
    List<MemberReceipt> getMemberReceipt(String receiptType, String memberId);

    /**
     * 添加会员发票
     *
     * @param memberReceipt 发票信息
     * @return
     */
    Boolean add(MemberReceipt memberReceipt);

}