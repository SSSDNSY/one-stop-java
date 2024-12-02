package fun.sssdnsy.common.query;

import fun.sssdnsy.common.util.BeanUtils;
import jakarta.transaction.SystemException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLTransientException;
import java.util.*;


/**
 * 通用查询器
 */
@Getter
@Setter
@ToString
public class QueryFilter {
    /**
     * 条件SQL的KEY
     */
    public static final String WHERE_SQL_TAG = "whereSql";
    /**
     * 排序SQL的KEY
     */
    public static final String ORDER_SQL_TAG = "orderBySql";
    /**
     * 分页信息
     */
    private PageBean pageBean;

    /**
     * 排序字段
     */
    private List<FieldSort> sorter = new ArrayList<FieldSort>();

    /**
     * 查询参数
     */
    private Map<String, Object> params = new LinkedHashMap<String, Object>();
    /**
     * 查询条件组
     */
    private List<QueryField> querys = new ArrayList<QueryField>();

    /**
     * @ApiModelProperty(name = "groupRelation", notes = "查询条件分组的关系")
     */
    private FieldRelation groupRelation = FieldRelation.AND;

    /**
     * 实体类型
     */
    private Class<?> clazz;

    public QueryFilter() {

    }

    public PageBean getPageBean() {
        return pageBean;
    }


    private QueryFilter(Class<?> clazz) {
        this.clazz = clazz;
    }

    public static org.kie.internal.query.QueryFilter build() {
        return new QueryFilter();
    }

    /**
     * 清除当前查询对象的入参
     *
     * @return
     */
    public QueryFilter clear() {
        this.params.clear();
        this.querys.clear();
        this.sorter.clear();
        return this;
    }

    /**
     * 构造器
     *
     * @param clazz 当前通用查询所对应实体类
     * @return
     */
    public static QueryFilter build(Class<?> clazz) {
        return new QueryFilter(clazz);
    }

    public QueryFilter withDefaultPage() {
        this.pageBean = new PageBean();
        return this;
    }

    public QueryFilter withPage(PageBean pageBean) {
        this.pageBean = pageBean;
        return this;
    }

    public QueryFilter withSorter(FieldSort fieldSort) {
        this.sorter.add(fieldSort);
        return this;
    }

    public QueryFilter withQuery(QueryField queryField) {
        this.querys.add(queryField);
        return this;
    }

    public QueryFilter withParam(String key, Object value) {
        this.params.put(key, value);
        return this;
    }

    public Map<String, Object> getParams() throws SystemException {

        // 生成SQL语句到params中
        generatorSQL();
        // 初始化查询元素中的查询参数到params中
        initParams(querys);
        return params;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public void addFilter(String property, Object value, QueryOP operation, FieldRelation relation) {
        QueryField queryField = new QueryField(property, value, operation, relation);
        querys.add(queryField);
    }

    public QueryFilter withFilter(String property, Object value, QueryOP operation, FieldRelation relation) {
        QueryField queryField = new QueryField(property, value, operation, relation);
        querys.add(queryField);
        return this;
    }

    public void addFilter(String property, Object value, QueryOP operation, FieldRelation relation, String group) {
        QueryField queryField = new QueryField(property, value, operation, relation, group);
        querys.add(queryField);
    }

    public QueryFilter withFilter(String property, Object value, QueryOP operation, FieldRelation relation, String group) {
        QueryField queryField = new QueryField(property, value, operation, relation, group);
        querys.add(queryField);
        return this;
    }

    public void addFilter(String property, Object value, QueryOP operation) {
        QueryField queryField = new QueryField(property, value, operation, FieldRelation.AND);
        querys.add(queryField);
    }

    public QueryFilter withFilter(String property, Object value, QueryOP operation) {
        QueryField queryField = new QueryField(property, value, operation, FieldRelation.AND);
        querys.add(queryField);
        return this;
    }

    public void addParams(String property, Object value) {
        params.put(property, value);
    }

    public List<FieldSort> getSorter() {
        return sorter;
    }

    public void setSorter(List<FieldSort> sorter) {
        this.sorter = sorter;
    }

    public List<QueryField> getQuerys() {
        return querys;
    }

    public void setQuerys(List<QueryField> querys) {
        this.querys = querys;
    }

    /**
     * 获取当前通用查询所对应的实体类
     *
     * @return 实体类
     */
    public Class<?> getClazz() {
        return this.clazz;
    }

    /**
     * 设置当前通用查询缩对应的实体类
     *
     * @param clazz 实体类
     */
    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    private void generatorSQL() throws SystemException {
        // 生成查询的SQL语句
        String querySQL = generatorQuerySQL();
        if (StringUtils.isNotEmpty(querySQL)) {
            params.put(WHERE_SQL_TAG, querySQL);
        }
        int orderSize = sorter.size();
        if (orderSize > 0) {
            StringBuffer sb = new StringBuffer();
            int i = 0;
            for (FieldSort fieldSort : sorter) {
                if (i++ > 0) {
                    sb.append(",");
                }
                sb.append(fieldSort.toSql(clazz));
            }
            params.put(ORDER_SQL_TAG, sb.toString());
        }
    }

    private String generatorQuerySQL() throws SQLTransientException {
        int size = querys.size();
        if (size == 0) return "";
        if (size == 1) {
            return querys.get(0).toSql(clazz);
        }
        if (size > 1) {
            Map<String, List<QueryField>> map = new HashMap<>();
            // 按照分组归类查询条件
            for (QueryField element : querys) {
                String group = element.getGroup();
                // 跳过标记字段组
                if (StringUtils.equalsAnyIgnoreCase(API_IGNORE_GROUP, group)) {
                    continue;
                }
                List<QueryField> list = map.get(group);
                if (BeanUtils.isEmpty(list)) {
                    list = new ArrayList<>();
                    map.put(group, list);
                }
                list.add(element);
            }
            List<StringBuffer> sbList = new ArrayList<>();

            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                StringBuffer sqlBuf = new StringBuffer();
                String group = it.next();
                List<QueryField> list = map.get(group);
                QueryField firstField = list.get(0);
                String relation = firstField.getRelation().value();
                int fieldList = list.size();
                for (int i = 0; i < fieldList; i++) {
                    if (i > 0) {
                        sqlBuf.append(" " + relation + " ");
                    }
                    sqlBuf.append(list.get(i).toSql(clazz));
                }
                if (fieldList > 1) {
                    sqlBuf.insert(0, " (");
                    sqlBuf.append(") ");
                }
                sbList.add(sqlBuf);
            }

            StringBuffer result = new StringBuffer();
            for (int i = 0; i < sbList.size(); i++) {
                if (i > 0) {
                    result.append(" " + groupRelation.value() + " ");
                }
                result.append(sbList.get(i).toString());
            }
            return result.toString();
        }
        return "";
    }

    // 初始化参数
    private void initParams(List<QueryField> querys) {
        if (BeanUtils.isEmpty(querys)) {
            return;
        }
        for (QueryField element : querys) {
            QueryField queryField = (QueryField) element;
            QueryOP operation = queryField.getOperation();
            String group = queryField.getGroup();
            // 跳过标记字段组
            if (StringUtils.equalsAnyIgnoreCase(API_IGNORE_GROUP, group)) {
                continue;
            }
            if (QueryOP.IS_NULL.equals(operation) || QueryOP.IN.equals(operation)) {
                continue;
            }
            String property = queryField.getProperty();
            if (property.indexOf(".") > -1) {
                property = property.substring(property.indexOf(".") + 1);
            }
            Object value = queryField.getValue();
            this.params.put(property, value);
        }
    }

    public FieldRelation getGroupRelation() {
        return groupRelation;
    }

    public void setGroupRelation(FieldRelation groupRelation) {
        this.groupRelation = groupRelation;
    }


    /**
     * 是否包含某属性
     *
     * @param property 属性
     * @return
     */
    public boolean hasProperty(String property) {
        for (int i = 0; i < querys.size(); i++) {
            if (StringUtils.equalsIgnoreCase(querys.get(i).getProperty(), property)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回某属性的值 若有
     *
     * @param property 属性
     * @return value 值
     */
    public Object getPropertyValue(String property) {
        Object value = null;
        if (hasProperty(property)) {
            for (int i = 0; i < querys.size(); i++) {
                QueryField queryField = querys.get(i);
                if (StringUtils.equalsIgnoreCase(queryField.getProperty(), property)) {
                    value = queryField.getValue();
                }
            }
        }
        return value;
    }

    /**
     * 返回某属性的字符串值
     *
     * @param property 属性
     * @return value 值
     */
    public String getStringProp(String property) {
        return getPropertyValue(property) != null && getPropertyValue(property) instanceof String ? getPropertyValue(property).toString() : null;
    }

    /**
     * 返回某属性的字符串值
     *
     * @param property 属性
     * @return value 值
     */
    public Boolean getBoolProp(String property) {
        return getPropertyValue(property) != null && getPropertyValue(property) instanceof Boolean ? (Boolean) getPropertyValue(property) : null;
    }

    /**
     * 返回某属性的Integer值
     *
     * @param property 属性
     * @return value 值
     */
    public Integer geIntegerProp(String property) {
        return getPropertyValue(property) != null && getPropertyValue(property) instanceof Integer ? (Integer) getPropertyValue(property) : null;
    }

    /**
     * 返回某属性的Double值
     *
     * @param property 属性
     * @return value 值
     */
    public Double geDoubleProp(String property) {
        return getPropertyValue(property) != null && getPropertyValue(property) instanceof Double ? (Double) getPropertyValue(property) : null;
    }

    /**
     * 返回某属性的Long值
     *
     * @param property 属性
     * @return value 值
     */
    public Long geLongProp(String property) {
        return getPropertyValue(property) != null && getPropertyValue(property) instanceof Long ? (Long) getPropertyValue(property) : null;
    }

    /**
     * 返回某属性的Char值
     *
     * @param property 属性
     * @return value 值
     */
    public Char geCharProp(String property) {
        return getPropertyValue(property) != null && getPropertyValue(property) instanceof Char ? (Char) getPropertyValue(property) : null;
    }

    /**
     * 返回某属性的Float值
     *
     * @param property 属性
     * @return value 值
     */
    public Float geFloatProp(String property) {
        return getPropertyValue(property) != null && getPropertyValue(property) instanceof Float ? (Float) getPropertyValue(property) : null;
    }

    /**
     * 返回某属性的Byte值
     *
     * @param property 属性
     * @return value 值
     */
    public Byte geByteProp(String property) {
        return getPropertyValue(property) != null && getPropertyValue(property) instanceof Byte ? (Byte) getPropertyValue(property) : null;
    }

}
