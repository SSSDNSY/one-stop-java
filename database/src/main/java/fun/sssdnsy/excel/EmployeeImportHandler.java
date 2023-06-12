package fun.sssdnsy.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.asyncexcel.core.DataParam;
import com.asyncexcel.core.ErrorMsg;
import com.asyncexcel.core.ExcelContext;
import com.asyncexcel.core.annotation.ExcelHandle;
import com.asyncexcel.core.importer.DataImportParam;
import com.asyncexcel.core.importer.ImportContext;
import com.asyncexcel.core.importer.ImportHandler;
import fun.sssdnsy.model.Employee;
import fun.sssdnsy.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-06-12
 */

@Slf4j
@ExcelHandle
public class EmployeeImportHandler implements ImportHandler<Employee> {

    @Autowired
    EmployeeService service;

    @Override
    public void init(ExcelContext ctx, DataParam param) {
        ReadSheet     readSheet = EasyExcel.readSheet().sheetNo(0).headRowNumber(2).build();
        ImportContext impCtx    = (ImportContext) ctx;
        impCtx.setReadSheet(readSheet);
    }

    @Override
    public void callBack(ExcelContext ctx, DataParam param) {
        ImportHandler.super.callBack(ctx, param);
    }

    @Override
    public List<ErrorMsg> importData(List<Employee> list, DataImportParam dataImportParam)
            throws Exception {
        List<ErrorMsg> errorList = new ArrayList<>();
        List<Employee> saveUsers = new ArrayList<>();
        int            i         = 1;
        for (Employee employee : list) {
            if (employee.getPhone().contains("00000000")) {
                ErrorMsg msg = new ErrorMsg(i++, "手机号包含太多0");
                errorList.add(msg);
            } else {
                saveUsers.add(employee);
            }
        }
        service.saveBatch(saveUsers);
        return errorList;
    }


    @Override
    public void beforePerPage(ImportContext ctx, List<Employee> list, DataImportParam param) throws Exception {
        ImportHandler.super.beforePerPage(ctx, list, param);
    }

    @Override
    public void afterPerPage(ImportContext ctx, List<Employee> list, DataImportParam param, List<ErrorMsg> errorMsgList) throws Exception {
        ImportHandler.super.afterPerPage(ctx, list, param, errorMsgList);
    }
}