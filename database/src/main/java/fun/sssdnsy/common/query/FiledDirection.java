package fun.sssdnsy.common.query;

/**
 * 排序对象
 */
public enum FiledDirection {

    /**
     * 正序
     */
    ASC,

    /**
     * 逆序
     */
    DESC;

    /**
     * 从值中获取排序枚举，默认正序
     *
     * @param value
     * @return
     */
    public static FiledDirection fromString(String value) {

        try {
            return FiledDirection.valueOf(value.toUpperCase());
        } catch (Exception e) {
            return ASC;
        }
    }
}
