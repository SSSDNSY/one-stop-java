package fun.sssdnsy.executor.query;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.sssdnsy.common.query.AbstractManagerImpl;
import fun.sssdnsy.common.query.MyBatisDao;
import fun.sssdnsy.common.query.PageList;
import fun.sssdnsy.common.query.QueryFilter;
import fun.sssdnsy.common.util.BeanToolkit;
import fun.sssdnsy.common.util.EasyExcelUtil;
import fun.sssdnsy.dto.clientobject.EmployeeCO;
import fun.sssdnsy.dto.query.EmployeeQry;
import fun.sssdnsy.repository.EmployeeRepository;
import fun.sssdnsy.repository.dao.EmployeeDao;
import fun.sssdnsy.repository.dataobject.EmployeeDO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.ttzero.excel.entity.ListSheet;
import org.ttzero.excel.entity.Workbook;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 查询执行器
 */
@Component
public class EmployQueryExecutor extends AbstractManagerImpl<String, EmployeeCO> {

    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private EmployeeDao employeeDao;

    @Override
    protected MyBatisDao<String, EmployeeCO> getDao() {
        return employeeDao;
    }

    public PageList<EmployeeCO> query(QueryFilter filter) throws SQLException {
        return super.query(filter);
    }

    public EmployeeCO get(String id) {
        EmployeeDO dataObject = employeeRepository.getById(id);
        EmployeeCO EmployeeCO = new EmployeeCO();
        BeanUtils.copyProperties(dataObject, EmployeeCO);
        return EmployeeCO;
    }

    public Page<EmployeeCO> page(EmployeeQry qry) {
        Page page = employeeRepository.page(new Page(qry.getCurrent(), qry.getSize()));
        List<EmployeeCO> employeeCOS = BeanToolkit.instance().copyList(page.getRecords(), EmployeeCO.class);
        page.setRecords(employeeCOS);
        return page;
    }

    public List<EmployeeCO> list() {
        List<EmployeeDO> employeeDOS = employeeRepository.list();
        List<EmployeeCO> employeeCOS = BeanToolkit.instance().copyList(employeeDOS, EmployeeCO.class);
        return employeeCOS;
    }

    public List<EmployeeCO> list(EmployeeQry qry) {
        List<EmployeeDO> employeeDOS = employeeRepository.list(Wrappers.<EmployeeDO>lambdaQuery().like(StringUtils.isNotBlank(qry.getId()), EmployeeDO::getName, qry.getId()).like(StringUtils.isNotBlank(qry.getName()), EmployeeDO::getName, qry.getName()).like(Objects.nonNull(qry.getAge()), EmployeeDO::getName, qry.getAge()).like(StringUtils.isNotBlank(qry.getPhone()), EmployeeDO::getName, qry.getPhone()).like(StringUtils.isNotBlank(qry.getWechat()), EmployeeDO::getName, qry.getWechat()).like(StringUtils.isNotBlank(qry.getQq()), EmployeeDO::getQq, qry.getQq()));
        List<EmployeeCO> employeeCOS = BeanToolkit.instance().copyList(employeeDOS, EmployeeCO.class);
        return employeeCOS;
    }

    /**
     * 使用easyExcel导出
     *
     * @param qry
     * @param response
     * @throws IOException
     */
    public void exportExcel(EmployeeQry qry, HttpServletResponse response) throws IOException {

        List<EmployeeCO> employeeCOS = this.page(qry).getRecords();

        String[] headList = new String[]{"账号", "姓名", "手机", "QQ", "微信", "地址", "年龄", "邮箱", "预览", "详情"};

        String[] fieldList = new String[]{"id", "name", "phone", "qq", "wechat", "address", "age", "email", "preview", "detail"};

        EasyExcelUtil.exportExcel(response, "员工明细", "员工明细", Arrays.asList(headList), Arrays.asList(fieldList), employeeCOS);

    }

    /**
     * 使用 EEC导出
     * <p>
     * 下面代码即可支持百万级、千万级数据导出
     *
     * @param qry
     * @param response
     * @throws IOException
     */
    public void eecExport(EmployeeQry qry, HttpServletResponse response) throws IOException {

        new Workbook()
                // 添加进度兼听代码，外部可观察写入数据量，可做导出进度也简单写日志
                .onProgress((sheet, rows) -> System.out.println(STR."\{sheet.getName()} 已写入: \{rows}"))
                //添加sheet
                .addSheet(new ListSheet<EmployeeCO>() {
                    //数据分片
                    @Override
                    protected List<EmployeeCO> more() {
                        return page(qry).getRecords();
                    }
                })
                //  直接写到Response流
                .writeTo(EasyExcelUtil.getOutputStream("员工信息", response));
    }


}
