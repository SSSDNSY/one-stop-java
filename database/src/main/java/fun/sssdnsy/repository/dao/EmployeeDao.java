package fun.sssdnsy.repository.dao;

import fun.sssdnsy.common.query.MyBatisDao;
import fun.sssdnsy.dto.clientobject.EmployeeCO;

import java.util.List;
import java.util.Map;

/**
 * @author pengzh
 * @desc
 * @since 2024-12-03
 */
public interface EmployeeDao extends MyBatisDao<String, EmployeeCO> {

    /**
     * 查询实体对象
     *
     * @param map 通用查询对象
     * @return 分页结果
     */
    List<EmployeeCO> query(Map<String, Object> map);


}
