package cn.lili.modules.store.entity.enums;

/**
 * 发票来源枚举
 *
 * @author pikachu
 * @since 2020年3月07日 上午11:04:25
 */
public enum ReceiptSourceEnum {
    /**
     * 平台
     */
    PLATFORM("平台"),
    /**
     * 店铺
     */
    STORE("店铺");

    private final String description;

    ReceiptSourceEnum(String des) {
        this.description = des;
    }

    public String description() {
        return this.description;
    }

    public String value() {
        return this.name();
    }
}
