package fun.sssdnsy.common.query;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * 业务类管理实体类接口
 */
public interface Manager<PK extends Serializable, T> {

    /**
     * 查询实体对象
     *
     * @param queryFilter 通用查询对象
     * @return 分页结果
     * @throws SQLException
     */
    PageList<T> query(QueryFilter queryFilter) throws SQLException;


}
