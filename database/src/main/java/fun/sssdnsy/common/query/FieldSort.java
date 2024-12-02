package fun.sssdnsy.common.query;

import fun.sssdnsy.common.util.FieldConvertUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * 排序对象
 */

@Getter
@Setter
@ToString
public class FieldSort implements Serializable {

    private static final long serialVersionUID = 1;

    /**
     * 注入表达式
     */
    private static String INJECTION_REGEX = "[A-Za-z0-9\\_\\-\\+\\.]+";

    /**
     * 排序
     */
    private FiledDirection filedDirection;

    /**
     * 属性
     */
    private String property;


    public FieldSort() {
    }

    public FieldSort(String property) {
        this(property, FiledDirection.ASC);
    }

    public FieldSort(String property, FiledDirection filedDirection) {
        this.filedDirection = filedDirection;
        this.property = property;
    }

    /**
     * 构造器
     *
     * @param property  排序属性
     * @param direction 排序方向
     * @return
     */
    public static FieldSort of(String property, String direction) {
        return new FieldSort(property, FiledDirection.fromString(direction));
    }

    /**
     * 构造器
     *
     * @param property 排序属性
     * @return
     */
    public static FieldSort of(String property) {
        return of(property, FiledDirection.ASC.name());
    }


    /**
     * 是否SQL注入
     *
     * @param str SQL语句
     * @return 是否为SQL注入
     */
    public static boolean isSQLInjection(String str) {
        return !Pattern.matches(INJECTION_REGEX, str);
    }

    /**
     * 装换为SQL语句
     *
     * @param clazz 实体类型
     * @return SQL语句
     */
    public String toSql(Class<?> clazz) {
        if (isSQLInjection(property)) {
            throw new IllegalArgumentException("SQLInjection property: " + property);
        }
        // 将实体类属性转换为数据库列名
        property = FieldConvertUtil.property2Field(property, clazz);
        return property + (filedDirection == null ? "" : " " + filedDirection.name());
    }

}
