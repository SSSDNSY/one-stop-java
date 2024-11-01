package excel;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-02-14
 */
public class ExcelCopyUtil {

    public static void main1(String[] args) throws Exception {
        File newFile = new File("F:\\WXWork\\1688850380048787\\Cache\\File\\2023-02\\业绩整改一览表.xlsx");
        File newFile1 = new File("C:\\Users\\op.138352\\Desktop\\test.xlsx");
        // 新文件写入数据，并下载
        InputStream is = null;
        XSSFWorkbook workbook = null;
        XSSFSheet sheet = null;
        XSSFSheet sheet1 = null;
        XSSFSheet sheet2 = null;
        POIFSFileSystem ps = null;
        try {
            is = new FileInputStream(newFile);// 将excel文件转为输入流
            ps = new POIFSFileSystem(is);
//            workbook = new XSSFWorkbook(ps);// 创建个workbook，
            // 获取第一个sheet
            sheet = workbook.getSheetAt(0);
            sheet1 = workbook.cloneSheet(1);
            sheet2 = workbook.cloneSheet(2);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        // 写数据
        FileOutputStream fos = new FileOutputStream(newFile1);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    public static void main(String[] args) throws Exception {
        File newFile = new File("F:\\WXWork\\1688850380048787\\Cache\\File\\2023-02\\业绩整改一览表.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook
                (new FileInputStream(newFile));
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        copyRow(workbook, sheet, 0, 1);
        FileOutputStream out = new FileOutputStream("C:\\Users\\op.138352\\Desktop\\test4.xlsx");
        workbook.write(out);
        out.close();
    }

    private static void copyRow(XSSFWorkbook workbook, XSSFSheet worksheet, int sourceRowNum, int destinationRowNum) {
        // Get the source / new row
        XSSFRow newRow = worksheet.getRow(destinationRowNum);
        XSSFRow sourceRow = worksheet.getRow(sourceRowNum);

        // If the row exist in destination, push down all rows by 1 else create a new row
        if (newRow != null) {
            worksheet.shiftRows(destinationRowNum, worksheet.getLastRowNum(), 1);
        } else {
            newRow = worksheet.createRow(destinationRowNum);
        }

        // Loop through source columns to add to new row
        for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
            // Grab a copy of the old/new cell
            XSSFCell oldCell = sourceRow.getCell(i);
            XSSFCell newCell = newRow.createCell(i);

            // If the old cell is null jump to next cell
            if (oldCell == null) {
                newCell = null;
                continue;
            }

            // Copy style from old cell and apply to new cell
            XSSFCellStyle newCellStyle = workbook.createCellStyle();
            newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
            ;
            newCell.setCellStyle(newCellStyle);

            // If there is a cell comment, copy
            if (oldCell.getCellComment() != null) {
                newCell.setCellComment(oldCell.getCellComment());
            }

            // If there is a cell hyperlink, copy
            if (oldCell.getHyperlink() != null) {
                newCell.setHyperlink(oldCell.getHyperlink());
            }

            // Set the cell data type
            newCell.setCellType(oldCell.getCellType());

            // Set the cell data value
            switch (oldCell.getCellType()) {
                case Cell.CELL_TYPE_BLANK:
                    newCell.setCellValue(oldCell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    newCell.setCellValue(oldCell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_ERROR:
                    newCell.setCellErrorValue(oldCell.getErrorCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    newCell.setCellFormula(oldCell.getCellFormula());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    newCell.setCellValue(oldCell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_STRING:
                    newCell.setCellValue(oldCell.getRichStringCellValue());
                    break;
            }


        }
    }
}
