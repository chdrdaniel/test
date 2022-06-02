package cn.lili.modules.group.entity.enums;

/**
 * 帮卖会员状态
 *
 * @author chc
 * @since 2022-5-20 14:21:02
 */
public enum GroupHelpStatusEnums {
    /**
     * 申请中
     */
    APPLY("申请中"),
    /**
     * 通过
     */
    PASS("通过"),
    /**
     * 已清退
     */
    RETREAT("已清退"),
    /**
     * 审核拒绝
     */
    REFUSE("审核拒绝");

    private final String description;

    GroupHelpStatusEnums(String description) {
        this.description = description;
    }
}
