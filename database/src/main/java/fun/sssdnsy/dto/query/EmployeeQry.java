package fun.sssdnsy.dto.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 彭智慧
 * @since 2024-11-08
 */
@Getter
@Setter
@ToString
public class EmployeeQry extends Page {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 手机
     */
    private String phone;

    /**
     * QQ
     */
    private String qq;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 地址
     */
    private String address;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 姓名
     */
    private String name;

    /**
     * 概述
     */
    private String preview;

    /**
     * 详情
     */
    private String detail;


}
