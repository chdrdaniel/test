package cn.lili.modules.group.entity.dos;

import cn.lili.common.security.AuthUser;
import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 帮卖
 *
 * @author chc
 * @since 2022-5-20 14:09:26
 */
@Data
@ApiModel(value = "帮卖")
@TableName(value = "li_group_help")
@NoArgsConstructor
@AllArgsConstructor
public class GroupHelp extends BaseEntity {
    @ApiModelProperty("会员Id")
    private String memberId;

    @ApiModelProperty("会员名称")
    private String memberName;

    @ApiModelProperty("会员头像")
    private String memberFace;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("店铺Id")
    private String storeId;

    @ApiModelProperty("店铺名称")
    private String storeName;

    public GroupHelp(AuthUser currentUser) {
        this.memberId = currentUser.getId();
        this.memberName = currentUser.getUsername();
        this.memberFace = currentUser.getFace();
    }
}
