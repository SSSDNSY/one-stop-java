package fun.sssdnsy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.sssdnsy.dto.clientobject.EmployeeCO;
import fun.sssdnsy.dto.command.EmployeeCmd;
import fun.sssdnsy.dto.query.EmployeeQry;
import fun.sssdnsy.repository.EmployeeRepository;
import fun.sssdnsy.repository.dataobject.EmployeeDO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
    private EmployeeRepository employeeRepository;

    public Page<EmployeeCO> page(EmployeeQry qry) {
        Page page = employeeRepository.page(new Page(qry.getCurrent(), qry.getSize()));
        return page;
    }

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

    public EmployeeCO get(String id) {
        EmployeeDO dataObject = employeeRepository.getById(id);
        EmployeeCO EmployeeCO = new EmployeeCO();
        BeanUtils.copyProperties(dataObject, EmployeeCO);
        return EmployeeCO;
    }

    public void delete(List<String> ids) {
        employeeRepository.removeByIds(ids);
    }

}