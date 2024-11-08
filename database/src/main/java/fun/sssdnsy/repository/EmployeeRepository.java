package fun.sssdnsy.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.sssdnsy.repository.dataobject.EmployeeDO;
import fun.sssdnsy.repository.mapper.EmployeeMapper;
import org.springframework.stereotype.Component;

/**
* 仓储实现
*
* @author 彭智慧
* @since 2024-11-08
*/

@Component
public class EmployeeRepository extends ServiceImpl<EmployeeMapper,EmployeeDO> {

}

