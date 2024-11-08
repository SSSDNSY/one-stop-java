package fun.sssdnsy.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.sssdnsy.repository.dataobject.EmployeeDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * Mapper 接口
 *
 * @author 彭智慧
 * @since 2024-11-08
 */

@Mapper
public interface EmployeeMapper extends BaseMapper<EmployeeDO> {

}
