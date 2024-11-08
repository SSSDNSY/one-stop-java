package fun.sssdnsy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.sssdnsy.common.DataResponse;
import fun.sssdnsy.dto.clientobject.EmployeeCO;
import fun.sssdnsy.dto.command.EmployeeCmd;
import fun.sssdnsy.dto.query.EmployeeQry;
import fun.sssdnsy.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 接口
 *
 * @author 彭智慧
 * @since 2024-11-08
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    /**
     * 分页查询
     */
    @PostMapping("/page")
    public DataResponse<Page<EmployeeCO>> page(@RequestBody EmployeeQry qry) {
        Page<EmployeeCO> page = employeeService.page(qry);
        return DataResponse.of(page);
    }

    /**
     * 创建
     */
    @PostMapping("/create")
    public DataResponse create(@RequestBody EmployeeCmd cmd) {
        return DataResponse.of(employeeService.create(cmd));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public DataResponse modify(@RequestBody EmployeeCmd cmd) {
        return DataResponse.of(employeeService.update(cmd));
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    public DataResponse<EmployeeCO> get(@PathVariable String id) {
        EmployeeCO co = employeeService.get(id);
        return DataResponse.of(co);
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    public DataResponse delete(@RequestBody List<String> ids) {
        employeeService.delete(ids);
        return DataResponse.success();
    }

}
