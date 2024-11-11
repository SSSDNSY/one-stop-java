package fun.sssdnsy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.sssdnsy.dto.clientobject.EmployeeCO;
import fun.sssdnsy.dto.command.EmployeeCmd;
import fun.sssdnsy.dto.query.EmployeeQry;
import fun.sssdnsy.executor.EmployeeUpdateExecutor;
import fun.sssdnsy.executor.query.EmployQueryExecutor;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * 服务类
 *
 * @author 彭智慧
 * @since 2024-11-08
 */
@Service
public class EmployeeService {


    @Resource
    private EmployQueryExecutor employQueryExecutor;

    @Resource
    private EmployeeUpdateExecutor employeeUpdateExecutor;


    public EmployeeCO get(String id) {
        return employQueryExecutor.get(id);
    }


    public Page<EmployeeCO> page(EmployeeQry qry) {
        return employQueryExecutor.page(qry);
    }

    public List<EmployeeCO> list() {
        return employQueryExecutor.list();
    }

    public List<EmployeeCO> list(EmployeeQry qry) {
        return employQueryExecutor.list(qry);
    }

    public void export(EmployeeQry qry, HttpServletResponse response) throws IOException {
        employQueryExecutor.exportExcel(qry, response);
    }

    public boolean create(EmployeeCmd cmd) {
        return employeeUpdateExecutor.create(cmd);
    }

    public boolean update(EmployeeCmd cmd) {
        return employeeUpdateExecutor.update(cmd);
    }

    public void delete(List<String> ids) {
        employeeUpdateExecutor.delete(ids);
    }

}