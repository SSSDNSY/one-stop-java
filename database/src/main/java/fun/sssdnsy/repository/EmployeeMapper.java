package fun.sssdnsy.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.sssdnsy.model.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-06-12
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {


}
