package fun.sssdnsy.common.util;

import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Bean tool
 */
@Slf4j
public class BeanUtils {

    /**
     * 可以用于判断 Map,Collection,String,Array,Long是否为空
     *
     * @param object java.lang.Object.
     * @return boolean.
     */
    public static boolean isEmpty(Object object) {
        if (object == null) return true;
        if (object instanceof String) {
            if (((String) object).trim().length() == 0) return true;
        } else if (object instanceof Collection) {
            if (((Collection<?>) object).size() == 0) return true;
        } else if (object.getClass().isArray()) {
            if (((Object[]) object).length == 0) return true;
        } else if (object instanceof Map map) {
            if (map.size() == 0) return true;
        } else if (object instanceof Serializable) {
            return object.toString().trim().length() == 0;
        } else if (object instanceof ArrayNode) {
            ArrayNode an = (ArrayNode) object;
            return an.isEmpty(null);
        }
        return false;
    }

    /**
     * 可以用于判断 Map,Collection,String,Array是否不为空
     *
     * @param object
     * @return
     */
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * 判断对象是否为数字
     *
     * @param o
     * @return
     */
    public static boolean isNumber(Object o) {
        if (o == null) return false;
        if (o instanceof Number) return true;
        if (o instanceof String) {
            try {
                Double.parseDouble((String) o);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }


    /**
     * 根据指定的类名判定指定的类是否存在。
     *
     * @param className
     * @return
     */
    public static boolean validClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * 判定类是否继承自父类
     *
     * @param cls         子类
     * @param parentClass 父类
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static boolean isInherit(Class cls, Class parentClass) {
        return parentClass.isAssignableFrom(cls);
    }

    /**
     * 输入基类包名，扫描其下的类，返回类的全路径
     *
     * @param basePackages 如：com.hotent
     * @return
     * @throws IllegalArgumentException
     */
    @SuppressWarnings("all")
    public static List<String> scanPackages(String basePackages) throws IllegalArgumentException {

        ResourcePatternResolver rl = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(rl);
        List result = new ArrayList();
        String[] arrayPackages = basePackages.split(",");
        try {
            for (int j = 0; j < arrayPackages.length; j++) {
                String packageToScan = arrayPackages[j];
                String packagePart = packageToScan.replace('.', '/');
                String classPattern = "classpath*:/" + packagePart + "/**/*.class";
                Resource[] resources = rl.getResources(classPattern);
                for (int i = 0; i < resources.length; i++) {
                    Resource resource = resources[i];
                    MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                    String className = metadataReader.getClassMetadata().getClassName();
                    result.add(className);
                }
            }
        } catch (Exception e) {
            new IllegalArgumentException("scan pakcage class error,pakcages:" + basePackages);
        }

        return result;
    }

    /**
     * java反射访问私有成员变量的值。
     *
     * @param instance
     * @param fieldName
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchFieldException   Object
     */
    public static Object getValue(Object instance, String fieldName) throws IllegalAccessException, NoSuchFieldException {

        Field field = getField(instance.getClass(), fieldName);
        // 参数值为true，禁用访问控制检查
        field.setAccessible(true);
        return field.get(instance);
    }

    /**
     * 将字符串数据按照指定的类型进行转换。
     *
     * @param typeName 实际的数据类型
     * @param value    字符串值。
     * @return Object
     */
    public static Object convertByActType(String typeName, String value) {
        Object o = null;
        if (typeName.equals("int")) {
            o = Integer.parseInt(value);
        } else if (typeName.equals("short")) {
            o = Short.parseShort(value);
        } else if (typeName.equals("long")) {
            o = Long.parseLong(value);
        } else if (typeName.equals("float")) {
            o = Float.parseFloat(value);
        } else if (typeName.equals("double")) {
            o = Double.parseDouble(value);
        } else if (typeName.equals("boolean")) {
            o = Boolean.parseBoolean(value);
        } else if (typeName.equals("java.lang.String")) {
            o = value;
        } else {
            o = value;
        }
        return o;
    }

    /**
     * 根据类和成员变量名称获取成员变量。
     *
     * @param thisClass
     * @param fieldName
     * @return
     * @throws NoSuchFieldException Field
     */
    public static Field getField(Class<?> thisClass, String fieldName) throws NoSuchFieldException {

        if (fieldName == null) {
            throw new NoSuchFieldException("Error field !");
        }

        Field field = thisClass.getDeclaredField(fieldName);
        return field;
    }

    /**
     * 合并两个对象。
     *
     * @param srcObj
     * @param desObj void
     */
    public static void mergeObject(Object srcObj, Object desObj) {
        if (srcObj == null || desObj == null) return;

        Field[] fs1 = srcObj.getClass().getDeclaredFields();
        Field[] fs2 = desObj.getClass().getDeclaredFields();
        for (int i = 0; i < fs1.length; i++) {
            try {
                fs1[i].setAccessible(true);
                Object value = fs1[i].get(srcObj);
                fs1[i].setAccessible(false);
                if (null != value) {
                    fs2[i].setAccessible(true);
                    fs2[i].set(desObj, value);
                    fs2[i].setAccessible(false);
                }
            } catch (Exception e) {
                log.error("mergeObject" + e.getMessage());
            }
        }
    }

    /**
     * 数据列表去重
     *
     * @param list
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
    }


}
