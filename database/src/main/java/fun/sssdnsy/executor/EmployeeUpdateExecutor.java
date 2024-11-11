package fun.sssdnsy.executor;

import fun.sssdnsy.dto.command.EmployeeCmd;
import fun.sssdnsy.repository.EmployeeRepository;
import fun.sssdnsy.repository.dataobject.EmployeeDO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 更新执行器
 */
@Component
public class EmployeeUpdateExecutor {

    @Resource
    private EmployeeRepository employeeRepository;


    public boolean create(EmployeeCmd cmd) {
        EmployeeDO dataObj = new EmployeeDO();
        BeanUtils.copyProperties(cmd, dataObj);
        boolean save = employeeRepository.save(dataObj);
        return save;
    }

    public boolean update(EmployeeCmd cmd) {
        EmployeeDO dataObj = new EmployeeDO();
        BeanUtils.copyProperties(cmd, dataObj);
        boolean save = employeeRepository.updateById(dataObj);
        return save;
    }

    public void delete(List<String> ids) {
        employeeRepository.removeByIds(ids);
    }

}
