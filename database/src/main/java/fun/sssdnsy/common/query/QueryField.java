package fun.sssdnsy.common.query;

import cn.hutool.extra.spring.SpringUtil;
import fun.sssdnsy.common.contants.SQLConst;
import fun.sssdnsy.common.util.BeanUtils;
import fun.sssdnsy.common.util.DateFormatUtil;
import fun.sssdnsy.common.util.FieldConvertUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLSyntaxErrorException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import static fun.sssdnsy.common.contants.DateTimeConst.DATE_FORMAT_DATETIME;
import static fun.sssdnsy.common.util.DateFormatUtil.PATTERN_DEFAULT_ON_SECOND;

/**
 * 构建通用查询条件
 */
@Getter
@Setter
@ToString
public class QueryField {

    /**
     * 实体类属性
     */
    private String property;

    /**
     * 比较符
     */
    private QueryOP operation = QueryOP.EQUAL;

    /**
     * 比较值
     */
    private Object value;

    /**
     * 同一个分组内的多个条件之间的组合关系，默认分组为AND
     */
    private FieldRelation relation = FieldRelation.AND;

    /**
     * 查询条件分组，默认分组为main，多个分组默认按照and的关系组合在一起
     */
    private String group = "main";

    //值是否已初始化

    /**
     * 值是否已初始化，默认分组为false
     */
    private Boolean hasInitValue = false;

    public QueryField() {
    }

    /**
     * 构造函数
     *
     * @param property 实体类属性
     * @param value    查询值
     */
    public QueryField(String property, Object value) {
        this(property, value, QueryOP.EQUAL, FieldRelation.AND);

    }

    /**
     * 构造函数
     *
     * @param property  实体类属性
     * @param value     查询值
     * @param operation 查询符号
     */
    public QueryField(String property, Object value, QueryOP operation) {
        this(property, value, operation, FieldRelation.AND);
    }

    /**
     * 构造函数
     *
     * @param property 实体类属性
     * @param value    查询值
     * @param relation 与其他查询条件的组合关系
     */
    public QueryField(String property, Object value, FieldRelation relation) {
        this(property, value, QueryOP.EQUAL, relation);
    }

    /**
     * 构造函数
     *
     * @param property 实体类属性
     * @param value    查询值
     * @param relation 与其他查询条件的组合关系
     */
    public QueryField(String property, Object value, QueryOP operation, FieldRelation relation) {
        this(property, value, operation, relation, null);
    }

    /**
     * 构造函数
     *
     * @param property  实体类属性
     * @param value     查询值
     * @param operation 查询符号
     * @param relation  与其他查询条件的组合关系
     */
    public QueryField(String property, Object value, QueryOP operation, FieldRelation relation, String group) {
        this.property = property;
        this.value = value;
        this.operation = operation;
        this.relation = relation;
        if (StringUtils.isNotEmpty(group)) {
            this.group = group;
        }
    }


    private void initSqlValue() {
        if (hasInitValue) {
            // 初始化值的操作只允许执行一次
            return;
        }
        hasInitValue = true;
        if (QueryOP.IN.equals(operation)) {
            this.value = getInValueSql();
        }
        if (BeanUtils.isNotEmpty(value)) {
            if (QueryOP.LIKE.equals(operation) && !value.toString().startsWith("%") && !value.toString().endsWith("%")) {
                this.value = "%" + value + "%";
            } else if (QueryOP.LEFT_LIKE.equals(operation) && !value.toString().startsWith("%")) {
                this.value = "%" + value;
            } else if (QueryOP.RIGHT_LIKE.equals(operation) && !value.toString().endsWith("%")) {
                this.value = value + "%";
            } else if (QueryOP.EQUAL_IGNORE_CASE.equals(operation)) {
                this.value = this.value.toString().toUpperCase();
            }
        }
    }

    /**
     * 将查询条件生成为SQL语句
     *
     * @param clazz 查询条件所对应的实体类
     * @return SQL语句
     */
    @SuppressWarnings("rawtypes")
    public String toSql(Class clazz) throws SQLSyntaxErrorException {
        initSqlValue();
        if (operation == null) {
            operation = QueryOP.EQUAL;
        }
        String fieldParam;
        if (property == null) {
            throw new SQLSyntaxErrorException("The 'property' in QueryField can not be empty.");
        }
        if (property.indexOf(".") > -1) {
            fieldParam = "#{" + property.substring(property.indexOf(".") + 1) + "}";
        } else {
            fieldParam = "#{" + property + "}";
        }
        String sql = FieldConvertUtil.property2Field(property, clazz);
        if (QueryOP.EQUAL.equals(operation)) {
            sql += " = " + fieldParam;
        } else if (QueryOP.EQUAL_IGNORE_CASE.equals(operation)) {
            sql = "upper(" + sql + ")" + " = " + fieldParam;
        } else if (QueryOP.LESS.equals(operation)) {
            sql += " < " + fieldParam;
        } else if (QueryOP.LESS_EQUAL.equals(operation)) {
            sql += " <= " + fieldParam;
        } else if (QueryOP.GREAT.equals(operation)) {
            sql += " > " + fieldParam;
        } else if (QueryOP.GREAT_EQUAL.equals(operation)) {
            sql += " >= " + fieldParam;
        } else if (QueryOP.NOT_EQUAL.equals(operation)) {
            sql += " != " + fieldParam;
        } else if (QueryOP.IFNULL.equals(operation)) {
            sql = " IFNULL(" + sql + ", '')" + " != " + fieldParam;
            ;
        } else if (QueryOP.LEFT_LIKE.equals(operation)) {
            sql += " like " + fieldParam;
        } else if (QueryOP.RIGHT_LIKE.equals(operation)) {
            sql += " like  " + fieldParam;
        } else if (QueryOP.LIKE.equals(operation)) {
            sql += " like  " + fieldParam;
        } else if (QueryOP.IS_NULL.equals(operation)) {
            sql += " is null ";
        } else if (QueryOP.NOTNULL.equals(operation)) {
            sql += " is not null";
        } else if (QueryOP.IN.equals(operation)) {
            String valueStr = this.value.toString();
            if (valueStr.startsWith("(") && valueStr.endsWith(")")) {
                valueStr = valueStr.replace("(", "");
                valueStr = valueStr.replace(")", "");
                String[] strList = valueStr.split(",");
                int len = strList.length;
                //防止当in中元素大于1000时，执行语句报错
                if (len > 1000) {
                    sql = getOutLimitInSql(sql, strList);
                } else {
                    sql += " in  " + this.value;
                }
            } else {
                sql += " in  " + this.value;
            }
        } else if (QueryOP.NOT_IN.equals(operation)) {
            sql += " not in  " + this.value;

        } else if (QueryOP.BETWEEN.equals(operation)) {
            sql += getBetweenSql();
        } else if (QueryOP.NOT_EQUAL_OR_NULL.equals(operation)) {
            sql = "(" + sql + " != " + fieldParam + " or " + sql + " is null)";
        } else {
            sql += " =  " + fieldParam;
        }
        return sql;
    }

    /**
     * 针对in查询方式，根据传入的value的类型做不同的处理。 value 是 string，则分隔字符串，然后合并为符合in规范的字符串。
     * value 是 list，则直接合并为符合in规范的字符串。
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    private String getInValueSql() {
        String inValueSql = "";
        if (BeanUtils.isEmpty(value)) {
            return inValueSql = "('')";
        }
        if (value instanceof String) { // 字符串形式，通过逗号分隔
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            StringTokenizer st = new StringTokenizer(value.toString(), ",");
            while (st.hasMoreTokens()) {
                sb.append("'");
                sb.append(st.nextToken());
                sb.append("'");
                sb.append(",");
            }
            sb = new StringBuilder(sb.substring(0, sb.length() - 1));
            sb.append(")");
            inValueSql = sb.toString();
        } else if (value instanceof Collection) { // 列表形式
            Collection<Object> objList = (Collection<Object>) value;
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            for (Object obj : objList) {
                if (obj.toString().startsWith("'") && obj.toString().endsWith("'")) {
                    sb.append(obj.toString());
                } else {
                    sb.append("'");
                    sb.append(obj.toString());
                    sb.append("'");
                }
                sb.append(",");
            }
            sb = new StringBuilder(sb.substring(0, sb.length() - 1));
            sb.append(")");
            inValueSql = sb.toString();
        } else if (value instanceof String[]) { // 列表形式
            String[] objList = (String[]) value;
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            for (Object obj : objList) {
                if (obj.toString().startsWith("'") && obj.toString().endsWith("'")) {
                    sb.append(obj.toString());
                } else {
                    sb.append("'");
                    sb.append(obj.toString());
                    sb.append("'");
                }
                sb.append(",");
            }
            sb = new StringBuilder(sb.substring(0, sb.length() - 1));
            sb.append(")");
            inValueSql = sb.toString();
        }
        return inValueSql;
    }

    @SuppressWarnings("unchecked")
    private String getBetweenSql() {
        StringBuilder sb = new StringBuilder();
        sb.append(" between ");
        DbTypeService dbTypeService = SpringUtil.getBean(DbTypeService.class);
        String dbType = dbTypeService.getDbType();
        if (this.value instanceof List) {
            List<Object> objList = (List<Object>) value;
            for (int i = 0; i < objList.size(); i++) {
                Object obj = objList.get(i);
                if (i == 1) {
                    sb.append(" and ");
                }
                if (obj instanceof LocalDateTime) {
                    String dateString = DateFormatUtil.format((LocalDateTime) obj, PATTERN_DEFAULT_ON_SECOND);
                    if (SQLConst.DB_ORACLE.equals(dbType)) {
                        sb.append("TO_DATE(substr('" + dateString + "',1,19),'yyyy-mm-dd hh24:mi:ss')");
                    } else {
                        sb.append("'" + dateString + "'");
                    }
                } else {
                    String dataStr = obj.toString();
                    if (dataStr.endsWith("Z")) {
                        try {
                            dataStr = dataStr.replace("Z", " UTC");//UTC是本地时间
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
                            LocalDateTime date = LocalDateTime.parse(dataStr, format);
                            dataStr = DateFormatUtil.format(date, DATE_FORMAT_DATETIME);
                        } catch (Exception e) {
                        }
                    }
                    if (SQLConst.DB_ORACLE.equals(dbType)) {
                        sb.append("TO_DATE(substr('" + dataStr + "',1,19),'yyyy-mm-dd hh24:mi:ss')");
                    } else {
                        sb.append("'" + dataStr + "'");
                    }
                }
            }
        }
        sb.append(" ");
        return sb.toString();
    }

    /**
     * 当in语句中包含元素大于1000个时的处理
     *
     * @param sql
     * @param strList
     * @return
     */
    private String getOutLimitInSql(String sql, String[] strList) {
        int index = 0;
        int times = 0;
        int i = 0;
        int len = strList.length;
        String field = sql;
        String[] newValue = new String[1000];
        StringBuilder newSql = new StringBuilder();
        newSql.append(" ( ");
        for (String str : strList) {
            newValue[index] = str;
            index++;
            i++;
            if (index % 1000 == 0) {
                if (times > 0) {
                    newSql.append(" or ");
                }
                times++;
                newSql.append(" ");
                newSql.append(field);
                newSql.append(" in ");
                newSql.append("(");
                newSql.append(StringUtils.join(newValue));
                newSql.append(")");
                newSql.append(" ");
                int size = len - 1000 * times;
                newValue = size >= 1000 ? new String[1000] : new String[size];
                index = 0;
            } else {
                if (i == len) {
                    if (times > 0) {
                        newSql.append(" or ");
                    }
                    times++;
                    newSql.append(" ");
                    newSql.append(field);
                    newSql.append(" in ");
                    newSql.append("(");
                    newSql.append(StringUtils.join(newValue));
                    newSql.append(")");
                    newSql.append(" ");
                }
            }
        }
        newSql.append(" ) ");
        return newSql.toString();
    }

}
