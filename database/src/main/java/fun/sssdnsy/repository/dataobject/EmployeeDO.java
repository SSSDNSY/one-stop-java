package fun.sssdnsy.repository.dataobject;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 
*
* @author 彭智慧
* @since 2024-11-08
*/
@Getter
@Setter
@ToString
@TableName("employee")
public class EmployeeDO implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
        * 主键
        */
    @TableId("id")
    private String id;

        /**
        * 手机
        */
    @TableField("phone")
    private String phone;

        /**
        * QQ
        */
    @TableField("qq")
    private String qq;

        /**
        * 微信
        */
    @TableField("wechat")
    private String wechat;

        /**
        * 地址
        */
    @TableField("address")
    private String address;

        /**
        * 年龄
        */
    @TableField("age")
    private Integer age;

        /**
        * 邮箱
        */
    @TableField("email")
    private String email;

        /**
        * 姓名
        */
    @TableField("name")
    private String name;

        /**
        * 概述
        */
    @TableField("preview")
    private String preview;

        /**
        * 详情
        */
    @TableField("detail")
    private String detail;


}
