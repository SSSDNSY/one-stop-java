package fun.sssdnsy.executor.query;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.sssdnsy.common.util.BeanToolkit;
import fun.sssdnsy.common.util.EasyExcelUtil;
import fun.sssdnsy.dto.clientobject.EmployeeCO;
import fun.sssdnsy.dto.query.EmployeeQry;
import fun.sssdnsy.repository.EmployeeRepository;
import fun.sssdnsy.repository.dataobject.EmployeeDO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 查询执行器
 */
@Component
public class EmployQueryExecutor {

    @Resource
    private EmployeeRepository employeeRepository;

    public EmployeeCO get(String id) {
        EmployeeDO dataObject = employeeRepository.getById(id);
        EmployeeCO EmployeeCO = new EmployeeCO();
        BeanUtils.copyProperties(dataObject, EmployeeCO);
        return EmployeeCO;
    }

    public Page<EmployeeCO> page(EmployeeQry qry) {
        Page page = employeeRepository.page(new Page(qry.getCurrent(), qry.getSize()));
        return page;
    }

    public List<EmployeeCO> list() {
        List<EmployeeDO> employeeDOS = employeeRepository.list();
        List<EmployeeCO> employeeCOS = BeanToolkit.instance().copyList(employeeDOS, EmployeeCO.class);
        return employeeCOS;
    }

    public List<EmployeeCO> list(EmployeeQry qry) {
        List<EmployeeDO> employeeDOS = employeeRepository.list(Wrappers.<EmployeeDO>lambdaQuery()
                .like(StringUtils.isNotBlank(qry.getId()), EmployeeDO::getName, qry.getId())
                .like(StringUtils.isNotBlank(qry.getName()), EmployeeDO::getName, qry.getName())
                .like(Objects.nonNull(qry.getAge()), EmployeeDO::getName, qry.getAge())
                .like(StringUtils.isNotBlank(qry.getPhone()), EmployeeDO::getName, qry.getPhone())
                .like(StringUtils.isNotBlank(qry.getWechat()), EmployeeDO::getName, qry.getWechat())
                .like(StringUtils.isNotBlank(qry.getQq()), EmployeeDO::getQq, qry.getQq())
        );
        List<EmployeeCO> employeeCOS = BeanToolkit.instance().copyList(employeeDOS, EmployeeCO.class);
        return employeeCOS;
    }

    public void exportExcel(EmployeeQry qry, HttpServletResponse response) throws IOException {

        List<EmployeeCO> employeeCOS = this.page(qry).getRecords();

        String[] headList = new String[]{"账号", "姓名", "手机", "QQ", "微信", "地址", "年龄", "邮箱", "预览", "详情"};

        String[] fieldList = new String[]{"id", "name", "phone", "qq", "wechat", "address", "age", "email", "preview", "detail"};

        EasyExcelUtil.exportExcel(response, "员工明细", "员工明细", Arrays.asList(headList), Arrays.asList(fieldList), employeeCOS);

    }


}
