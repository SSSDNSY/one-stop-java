package fun.sssdnsy.common.util;

import cn.hutool.extra.spring.SpringUtil;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.Collection;

/**
 * 实体类属性和物理表字段互转工具类
 */
public class FieldConvertUtil {
    /**
     * 实体类属性转换为数据库列名
     *
     * @param property 实体类属性
     * @param clazz    实体类
     * @return 数据库列名
     */
    @SuppressWarnings({"rawtypes"})
    public static String property2Field(String property, Class clazz) {
        SqlSessionTemplate sqlSessionTemplate = SpringUtil.getBean(SqlSessionTemplate.class);
        Configuration configuration = sqlSessionTemplate.getConfiguration();
        Collection<String> resultMapNames = configuration.getResultMapNames();
        for (String name : resultMapNames) {
            if (name.indexOf(".") == -1) {
                continue;
            }
            ResultMap resultMap = configuration.getResultMap(name);
            Class<?> type = resultMap.getType();
            if (type.equals(clazz)) {
                for (ResultMapping resultMappirng : resultMap.getPropertyResultMappings()) {
                    if (resultMappirng.getProperty().equals(property)) {
                        property = resultMappirng.getColumn();
                        return property;
                    }
                }
            }
        }
        return property;
    }

    /**
     * 数据库列名转换为实体类属性
     *
     * @param field 数据库列名
     * @param clazz 实体类
     * @return 实体类属性
     */
    @SuppressWarnings("rawtypes")
    public static String field2Property(String field, Class clazz) {
        String classNameSpace = clazz.getName() + ".";
        SqlSessionTemplate sqlSessionTemplate = SpringUtil.getBean(SqlSessionTemplate.class);
        Configuration configuration = sqlSessionTemplate.getConfiguration();
        Collection<String> resultMapNames = configuration.getResultMapNames();
        for (String name : resultMapNames) {
            if (name.indexOf(".") == -1) {
                continue;
            }
            ResultMap resultMap = configuration.getResultMap(name);
            if (resultMap.getId().indexOf(classNameSpace) != -1) {
                for (ResultMapping resultMappirng : resultMap.getPropertyResultMappings()) {
                    if (resultMappirng.getColumn().equals(field)) {
                        field = resultMappirng.getProperty();
                        return field;
                    }
                }
            }
        }
        return field;
    }
}
