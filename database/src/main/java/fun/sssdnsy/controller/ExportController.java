package fun.sssdnsy.controller;

import com.asyncexcel.core.exporter.DataExportParam;
import com.asyncexcel.core.importer.DataImportParam;
import com.asyncexcel.springboot.ExcelService;
import fun.sssdnsy.excel.EmployeeExportHandler;
import fun.sssdnsy.excel.EmployeeImportHandler;
import fun.sssdnsy.model.Employee;
import fun.sssdnsy.service.EmployeeService;
import fun.sssdnsy.util.ExcelUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-06-12
 */
@RestController
@RequestMapping("/export")
public class ExportController {

    @Resource
    EmployeeService employeeService;

    @GetMapping("/poi")
    public DeferredResult<ResponseEntity<byte[]>> poiExport() {
        DeferredResult<ResponseEntity<byte[]>> deferredResult = new DeferredResult<>();

        employeeService.poiExport().addCallback(employees -> {
            // 创建Excel文件并填充数据
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Employees");
            int rowNum = 0;
            // 在sheet中填充employees的数据
            // 创建表头
            Row headerRow = sheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Department");
            headerRow.createCell(3).setCellValue("Salary");

            // 填充数据
            for (Employee employee : employees) {
                Row dataRow = sheet.createRow(rowNum++);
                String id = employee.getId();
                String wechat = employee.getWechat();
                String phone = employee.getPhone();
                String name = employee.getName();
                Integer age = employee.getAge();
                String email = employee.getEmail();
                String address = employee.getAddress();
                String preview = employee.getPreview();
                String detail = employee.getDetail();

                dataRow.createCell(0).setCellValue(employee.getId());
                dataRow.createCell(1).setCellValue(employee.getName());
                dataRow.createCell(2).setCellValue(employee.getPhone());
                dataRow.createCell(3).setCellValue(employee.getWechat());
                dataRow.createCell(4).setCellValue(employee.getAddress());
                dataRow.createCell(5).setCellValue(employee.getDetail());
                dataRow.createCell(6).setCellValue(employee.getPreview());
                dataRow.createCell(7).setCellValue(employee.getEmail());
                dataRow.createCell(8).setCellValue(employee.getAge());
                dataRow.createCell(9).setCellValue(employee.getAge());
            }
            // 将Workbook转换为字节数组
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                workbook.write(byteArrayOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] excelBytes = byteArrayOutputStream.toByteArray();

            // 设置HTTP响应头信息
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "employees.xlsx");

            deferredResult.setResult(new ResponseEntity<>(excelBytes, headers, HttpStatus.OK));
        }, error -> {
            deferredResult.setErrorResult("Error.");
        });
        return deferredResult;
    }

    @GetMapping("/easy")
    public DeferredResult exportData() {
        DeferredResult<String> stringDeferredResult = new DeferredResult<>();
        stringDeferredResult.setResult("OK");
        List<Employee> employees = employeeService.selectAll();
        // 创建Excel文件并填充数据
        ExcelUtil.exportDataToExcel(employees, this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + ".xlsx");
        return stringDeferredResult;
    }


    @Resource
    ExcelService excelService;

    // 导入最简示例
    @PostMapping("/imports")
    public Long imports(@RequestBody MultipartFile file) throws Exception {
        DataImportParam dataImportParam = new DataImportParam()
                .setStream(file.getInputStream())
                .setModel(Employee.class)
                .setBatchSize(3)
                .setFilename("用户导入");
        Long taskId = excelService.doImport(EmployeeImportHandler.class, dataImportParam);
        return taskId;
    }

    // 导出最简示例
    @GetMapping("/asyncexcel")
    public Long exports() {
        DataExportParam dataExportParam = new DataExportParam()
                .setExportFileName("用户导出")
                .setLimit(5)
                .setHeadClass(Employee.class);
        return excelService.doExport(EmployeeExportHandler.class, dataExportParam);
    }
}
