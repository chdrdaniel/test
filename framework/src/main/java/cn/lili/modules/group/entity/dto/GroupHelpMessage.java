package cn.lili.modules.group.entity.dto;

import cn.lili.modules.group.entity.dos.GroupHelp;
import cn.lili.modules.group.entity.enums.GroupHelpStatusEnums;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 申请帮卖会员信息
 *
 * @Author chc
 * @since 2022/5/27 10:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupHelpMessage {

    @ApiModelProperty("店铺Id")
    private String stroreId;
    @ApiModelProperty("店铺名称")
    private String stroreName;
    @ApiModelProperty("会员Id")
    private String memberId;
    @ApiModelProperty("会员名称")
    private String memberName;
    @ApiModelProperty("新状态")
    private GroupHelpStatusEnums newStatus;

    public GroupHelpMessage(GroupHelp groupHelp) {
        this.stroreId = groupHelp.getStoreId();
        this.stroreName = groupHelp.getStoreName();
        this.memberId = groupHelp.getMemberId();
        this.memberName = groupHelp.getMemberName();
        this.newStatus = GroupHelpStatusEnums.valueOf(groupHelp.getStatus());
    }
}
