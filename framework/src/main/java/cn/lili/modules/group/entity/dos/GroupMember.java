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
 * 团员
 *
 * @author chc
 * @since 2022-5-19 16:37:18
 */
@Data
@ApiModel("团员")
@TableName("li_group_member")
@NoArgsConstructor
@AllArgsConstructor
public class GroupMember extends BaseEntity {

    private static final long serialVersionUID = 6557857522472457538L;

    @ApiModelProperty("团长Id")
    private String headId;

    @ApiModelProperty("团员Id")
    private String memberId;

    @ApiModelProperty("团员名称")
    private String memberName;

    @ApiModelProperty("团员头像")
    private String memberFace;

    @ApiModelProperty("消费金额")
    private Double consumptionAmount;

    @ApiModelProperty("退款金额")
    private Double refund;

    @ApiModelProperty("跟团次数")
    private Integer followCount;

    @ApiModelProperty("浏览次数")
    private Integer browseCount;

    @ApiModelProperty("带来人数")
    private Integer bringCount;

    @ApiModelProperty("积分")
    private Integer points;

    @ApiModelProperty("是否帮卖")
    private Boolean helpFlag;

    @ApiModelProperty("是否订阅")
    private Boolean subscribeFlag;

    @ApiModelProperty("是否管理员")
    private Boolean adminFlag;

    @ApiModelProperty("是否供货团长")
    private Boolean supplyFlag;

    public GroupMember(AuthUser currentUser){
        this.memberFace=currentUser.getFace();
        this.memberId=currentUser.getId();
        this.memberName=currentUser.getUsername();
        this.bringCount=0;
        this.points=0;
        this.refund=0.00;
        this.followCount=1;
    }
}
