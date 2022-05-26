package cn.lili.modules.order.order.entity.enums;


/**
 * 发票类型枚举
 *
 * @author qiuqiu
 * @since 2022/05/21 7:25 下午
 */
public enum ReceiptTypeEnum {

    ELECTRONIC("电子普通发票"),
    VAT_SPECIAL("增值税专用发票"),
    VAT("增值税普通发票");

    private final String description;

    ReceiptTypeEnum(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}
