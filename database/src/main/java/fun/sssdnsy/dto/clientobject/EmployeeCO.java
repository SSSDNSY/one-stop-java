package fun.sssdnsy.dto.clientobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.ttzero.excel.annotation.ExcelColumn;

import java.io.Serializable;

/**
 * @author 彭智慧
 * @since 2024-11-08
 */
@Getter
@Setter
@ToString
public class EmployeeCO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelColumn
    private String id;

    /**
     * 手机
     */
    @ExcelColumn
    private String phone;

    /**
     * QQ
     */
    @ExcelColumn
    private String qq;

    /**
     * 微信
     */
    @ExcelColumn
    private String wechat;

    /**
     * 地址
     */
    @ExcelColumn
    private String address;

    /**
     * 年龄
     */
    @ExcelColumn
    private Integer age;

    /**
     * 邮箱
     */
    @ExcelColumn
    private String email;

    /**
     * 姓名
     */
    @ExcelColumn
    private String name;

    /**
     * 概述
     */
    @ExcelColumn
    private String preview;

    /**
     * 详情
     */
    @ExcelColumn
    private String detail;


}
