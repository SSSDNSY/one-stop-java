package fun.sssdnsy.common.query;

import com.github.pagehelper.PageHelper;
import fun.sssdnsy.common.util.BeanUtils;
import jakarta.transaction.SystemException;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

/**
 * 抽象实体业务管理类实现
 */
public abstract class AbstractManagerImpl<PK extends Serializable, T extends Serializable> implements Manager<PK, T> {

    /**
     * 软删除字段名
     */
    public static final String DELETED = "deleted";
    /**
     * 展示软删除数据
     */
    public static final String SHOW_DELETED = "_show_deleted_";

    /**
     * 获取子类实现
     */
    protected abstract MyBatisDao<PK, T> getDao();

    /**
     * 获取当前泛型的类型
     *
     * @return 类型
     */
    @SuppressWarnings("unchecked")
    private Class<? super T> getTypeClass() {
        // 获取第二个泛型(T)对应的class
        Class<? super T> rawType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return rawType;
    }

    /**
     * 检查通用查询对象中的实体类型
     * <pre>
     * 1.若实体类型为空，使用当前的泛型补充；
     * 2.若实体类不为空，使用当前泛型类检查，不一致则抛出异常。
     * </pre>
     *
     * @param queryFilter
     * @throws SystemException
     */
    protected void handleQueryFilter(QueryFilter queryFilter) throws SQLException {
        if (BeanUtils.isEmpty(queryFilter)) {
            throw new SQLException("QueryFilter通用查询对象不能为空.");
        }
        Class<?> clazz = queryFilter.getClazz();
        Class<? super T> typeClass = getTypeClass();
        if (BeanUtils.isEmpty(clazz)) {
            // 所传入的通用查询器未指定 对应实体类时，从当前泛型中获取
            queryFilter.setClazz(typeClass);
        } else {
            if (!clazz.equals(typeClass)) {
                throw new SQLException(String.format("QueryFilter中的实体类:%s与Dao泛型中的实体类:%s不一致.", clazz, typeClass));
            }
        }
    }

    /**
     * 查询实体对象
     *
     * @param queryFilter 通用查询对象
     * @return 分页结果
     * @throws SystemException
     */
    public PageList<T> query(QueryFilter queryFilter) throws SQLException {
        handleQueryFilter(queryFilter);
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.isShowTotal());
        }
        filterDeleted(DELETED, queryFilter);
        List<T> query = getDao().query(queryFilter.getParams());
        PageList<T> pageList = new PageList<>(query);
        if (BeanUtils.isEmpty(pageBean)) {
            pageList.setTotal(pageList.getRows().size());
        }
        return pageList;
    }

    /**
     * 过滤删除数据
     *
     * @param deleted
     * @param queryFilter
     */
    private void filterDeleted(String deleted, QueryFilter queryFilter) {
        if (!queryFilter.hasProperty(SHOW_DELETED) && ReflectionUtils.findField(getTypeClass(), deleted) != null) {
            queryFilter.withFilter(DELETED, 0, QueryOP.EQUAL);
        }
    }

}
