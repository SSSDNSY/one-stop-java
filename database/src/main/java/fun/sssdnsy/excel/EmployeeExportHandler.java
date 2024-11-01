package fun.sssdnsy.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.asyncexcel.core.DataParam;
import com.asyncexcel.core.ExcelContext;
import com.asyncexcel.core.ExportPage;
import com.asyncexcel.core.annotation.ExcelHandle;
import com.asyncexcel.core.exporter.DataExportParam;
import com.asyncexcel.core.exporter.ExportContext;
import com.asyncexcel.core.exporter.ExportHandler;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.sssdnsy.model.Employee;
import fun.sssdnsy.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-06-12
 */

@Slf4j
@ExcelHandle
public class EmployeeExportHandler implements ExportHandler<Employee> {

    @Autowired
    EmployeeService employeeService;

    @Override
    public void init(ExcelContext ctx, DataParam param) {
        ExportContext context = (ExportContext) ctx;
        // 此处的sheetNo会被覆盖，为了兼容一个文件多sheet导出
        WriteSheet sheet = EasyExcel.writerSheet(0, "第一个sheet").head(Employee.class).build();
        context.setWriteSheet(sheet);
    }

    @Override
    public ExportPage<Employee> exportData(int startPage, int limit, DataExportParam dataExportParam) {
        IPage<Employee> iPage = new Page<>(startPage, limit);
        IPage page = employeeService.page(iPage);
        List<Employee> records = (List<Employee>) page.getRecords();
        ExportPage<Employee> result = new ExportPage<>();
        result.setTotal(page.getTotal());
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setRecords(records);
        return result;
    }

    @Override
    public void beforePerPage(ExportContext ctx, DataExportParam param) {
        // 分页执行，每页开始执行前
    }

    @Override
    public void afterPerPage(List<Employee> list, ExportContext ctx, DataExportParam param) {
        // 分页执行，每页执行完成后
    }

    @Override
    public void callBack(ExcelContext ctx, DataParam param) {
        // 全部执行完成后回调
        log.info("全部执行完成后回调");
    }


   /* public static <S, T> List<T> transform(List<S> source, Class<T> clazz) {
        if (CollectionUtils.isEmpty(source)) {
            return Lists.newArrayList();
        }
        BeanCopier beanCopier = BeanCopier.create(source.get(0).getClass(), clazz, false);

        Function<S, T> function = s -> {
            T t = null;
            try {
                t = clazz.newInstance();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            beanCopier.copy(s, t, null);
            return t;
        };
        List<T> list = Lists.transform(source, function);
        return list;
    }
*/
}