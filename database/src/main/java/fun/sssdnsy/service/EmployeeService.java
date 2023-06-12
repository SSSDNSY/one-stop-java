package fun.sssdnsy.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.sssdnsy.model.Employee;
import fun.sssdnsy.repository.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-06-12
 */
@Service
public class EmployeeService extends ServiceImpl<EmployeeMapper, Employee> {

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> selectAll() {
        return employeeMapper.selectList(Wrappers.lambdaQuery());
    }

    @Async
    public ListenableFuture<List<Employee>> poiExport() {
        List<Employee> employees = selectAll();
        return new AsyncResult<>(employees);
    }

}
