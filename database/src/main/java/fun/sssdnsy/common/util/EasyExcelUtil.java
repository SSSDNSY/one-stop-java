package fun.sssdnsy.common.util;

import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import static fun.sssdnsy.common.util.DateFormatUtil.PATTERN_DEFAULT_ON_SECOND;


/**
 * 关于alibaba的easyExcel用法，参考文档 https://www.yuque.com/easyexcel/doc/write
 */
public class EasyExcelUtil {
    /**
     * 使用easyExcel导出
     *
     * @param response
     * @param fileName      导出文件名
     * @param head          实体类
     * @param data          数据源
     * @param excludeColumn 排除的列
     */
    public static void exportExcel(HttpServletRequest request, HttpServletResponse response, String fileName, Class head, List data, Set<String> excludeColumn) throws IOException {
        excludeColumn.addAll(getDefaultExcludeColumn());
        downloadExcel(request, response, fileName);
        EasyExcel.write(response.getOutputStream(), head)
                .registerWriteHandler(new CustomCellWriteHandler())
                .excludeColumnFiledNames(excludeColumn)
                .sheet(fileName)
                .doWrite(data);
    }


    /**
     * 使用easyExcel导出
     *
     * @param response
     * @param fileName
     * @param head
     * @param data
     */
    public static void exportExcel(HttpServletRequest request, HttpServletResponse response, String fileName, Class head, List data) throws IOException {
        downloadExcel(request, response, fileName);
        EasyExcel.write(response.getOutputStream(), head)
                .excludeColumnFiledNames(getDefaultExcludeColumn())
                .registerWriteHandler(new CustomCellWriteHandler())
                .sheet(fileName)
                .doWrite(data);
    }

    /**
     * 获取baseModel里默认的字段
     *
     * @return
     */
    public static Set<String> getDefaultExcludeColumn() {
        Set<String> excludeColumnFiledNames = new HashSet<>();
        excludeColumnFiledNames.add("createBy");
        excludeColumnFiledNames.add("updateBy");
        excludeColumnFiledNames.add("createTime");
        excludeColumnFiledNames.add("updateTime");
        excludeColumnFiledNames.add("createOrgId");
        excludeColumnFiledNames.add("typeId");
        excludeColumnFiledNames.add("typeName");
        return excludeColumnFiledNames;
    }


    /**
     * @param response  web响应
     * @param fileName  文件名
     * @param sheetName excel表格sheet名
     * @param headList  列名
     * @param fieldList 类字段属性
     * @param dataList  数据list
     */
    public static void exportExcel(HttpServletResponse response, String fileName, String sheetName,
                                   List<String> headList, List<String> fieldList,
                                   List dataList) {
        try {
            if (StringUtils.isEmpty(sheetName)) {
                sheetName = "Sheet1";
            }
            if (response == null) {
                EasyExcel.write(fileName).head(head(headList)).sheet(sheetName).doWrite(dataList(fieldList, dataList));
            } else {
                EasyExcel.write(getOutputStream(fileName, response)).head(head(headList)).sheet(sheetName).doWrite(dataList(fieldList, dataList));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回流
     *
     * @param fileName
     * @param response
     * @return
     * @throws Exception
     */
    public static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf8");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()) + ".xlsx");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new Exception("导出excel表格失败!", e);
        }
    }

    /**
     * 通过自定义表头导出excel文件
     *
     * @param fileName   导出文件名
     * @param columnList 列名
     * @param fieldList  类字段属性
     * @param objectList 数据list
     * @throws IOException
     */
    public static void exportExcelFile(String fileName, List<String> columnList, List<String> fieldList, List objectList) throws IOException {
        EasyExcel.write(fileName).head(head(columnList)).sheet().doWrite(dataList(fieldList, objectList));
    }

    /**
     * @param columnList
     * @return
     */
    public static List<List<String>> head(List<String> columnList) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (String col : columnList) {
            List<String> head = new ArrayList<String>();
            head.add(col);
            list.add(head);
        }
        return list;
    }

    /**
     * 将 对象list 转换成 mapList
     *
     * @param objectList
     * @return
     */
    static List<Map<String, Object>> transObject(List<T> objectList) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            for (Object obj : objectList) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.setDateFormat(new SimpleDateFormat(PATTERN_DEFAULT_ON_SECOND));
                Map m = mapper.readValue(mapper.writeValueAsString(obj), Map.class);
                mapList.add(m);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapList;
    }

    /**
     * 根据 fieldList 组织数据
     *
     * @param fieldList
     * @param objectList
     * @return
     */
    public static List<List<Object>> dataList(List<String> fieldList, List<T> objectList) {
        List<Map<String, Object>> mapList = transObject(objectList);
        List<List<Object>> list = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            List<Object> data = new ArrayList<>();
            for (String field : fieldList) {
                data.add(map.get(field));
            }
            list.add(data);
        }
        return list;
    }

    /**
     * @param fileName  文件名
     * @param heads     表头
     * @param columns   表头对应字段
     * @param dataList  数据
     * @return 阿里云oss相对路径
     * @throws IOException
     */
    public static String export(String fileName, List<String> heads, List<String> columns, List dataList) throws IOException {
//        String date = DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD_HH_mm_ss);
//        //下载链接
//        String url = null;
//        fileName = fileName + "(" + date + ").xlsx";
//        EasyExcelUtil.exportExcelFile(fileName, heads, columns, dataList);
//
//        //创建临时文件
//        try (InputStream in = new FileInputStream(fileName)) {
//            // 上传阿里云
//            OssFacade ossFacade = SpringContextUtil.getBean(OssFacade.class);
//            ossFacade.saveObject(in, "train/" + date + "/" + fileName);
//            url = "train/" + date + "/" + fileName;
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            FileUtils.forceDelete(new File(fileName));
//        }
//        return url;
        return fileName;
    }


    /**
     * 设置下载文件名
     *
     * @param response
     * @param fileName
     */
    private static void downloadExcel(HttpServletRequest request, HttpServletResponse response, String fileName) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("Content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + encodeFileName(request, fileName) + ".xlsx");
    }


    /**
     * 文件名编码
     *
     * @param request
     * @param fileName
     * @return
     */
    public static String encodeFileName(HttpServletRequest request, String fileName) {
        if (request == null) {
            return fileName;
        }
        String userAgent = request.getHeader("USER-AGENT");
        try {
            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
                fileName = URLEncoder.encode(fileName, "utf-8");
            } else {
                fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }


}
