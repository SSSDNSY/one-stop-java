package fun.sssdnsy.util;

import com.alibaba.excel.EasyExcel;
import fun.sssdnsy.model.Employee;

import java.util.List;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-06-12
 */
public class ExcelUtil {

    public static void exportDataToExcel(List<Employee> employees, String filePath) {
        EasyExcel.write(filePath, Employee.class).sheet("Employee Data").doWrite(employees);
    }

}
