package fun.sssdnsy.common.query;

/**
 * 查询字段和值的操作类型枚举
 *
 * @author heyifan
 * @date 2017年12月12日
 */
public enum QueryOP {
    EQUAL("EQ", "=", "等于"),
    EQUAL_IGNORE_CASE("EIC", "=", "等于忽略大小写"),
    LESS("LT", "<", "小于"),
    GREAT("GT", ">", "大于"),
    LESS_EQUAL("LE", "<=", "小于等于"),
    GREAT_EQUAL("GE", ">=", "大于等于"),
    NOT_EQUAL("NE", "!=", "不等于"),
    IFNULL("IL", "IFNULL", "IFNULL函数"),
    LIKE("LK", "like", "相似"),
    LEFT_LIKE("LFK", "like", "左相似"),
    RIGHT_LIKE("RHK", "like", "右相似"),
    IS_NULL("ISNULL", "is null", "为空"),
    NOTNULL("NOTNULL", "is not null", "非空"),
    IN("IN", "in", "在...中"),
    NOT_IN("NOTIN", "not in", "不在...中"),
    BETWEEN("BETWEEN", "between", "在...之间"),
    NOT_EQUAL_OR_NULL("NOT_EQUAL_OR_NULL", "!=", "不等于或为空");

    private String val;
    private String op;
    private String desc;

    QueryOP(String val, String op, String desc) {
        this.val = val;
        this.op = op;
        this.desc = desc;
    }

    public String value() {
        return val;
    }

    public String op() {
        return op;
    }

    public String desc() {
        return desc;

    }

    /**
     * 根据运算符获取QueryOp
     *
     * @param op
     * @return QueryOP
     * @throws
     * @since 1.0.0
     */
    public static QueryOP getByOP(String op) {
        for (QueryOP queryOP : values()) {
            if (queryOP.op().equals(op)) {
                return queryOP;
            }
        }
        return null;
    }

    public static QueryOP getByVal(String val) {
        // 前端使用了LIKE  正常来说应该是LK  改了前端可能存在不可预测的问题 暂时先这样解决
        if ("LIKE".equals(val)) {
            return LIKE;
        }
        for (QueryOP queryOP : values()) {
            if (queryOP.val.equals(val)) {
                return queryOP;
            }
        }
        return null;
    }
}
