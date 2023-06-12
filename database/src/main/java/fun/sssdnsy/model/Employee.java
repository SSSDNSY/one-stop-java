package fun.sssdnsy.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-06-12
 */

@Entity
@Table(name = "employee")
@TableName("employee")
@Data
public class Employee implements Serializable {

    @TableId
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "wechat")
    private String wechat;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "preview")
    private String preview;

    @Column(name = "detail")
    private String detail;
}
