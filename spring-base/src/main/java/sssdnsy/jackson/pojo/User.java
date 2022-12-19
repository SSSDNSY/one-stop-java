/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sssdnsy.jackson.pojo;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pengzh
 * @desc
 * @class User
 * @since 2022-02-11
 *
 *
 *
 *
注解	用法
 @JsonProperty	用于属性，把属性的名称序列化时转换为另外一个名称。示例：@JsonProperty("birth_date") private Date birthDate
 @JsonIgnore	可用于字段、getter/setter、构造函数参数上，作用相同，都会对相应的字段产生影响。使相应字段不参与序列化和反序列化。
 @JsonIgnoreProperties	该注解是类注解。该注解在Java类和JSON不完全匹配的时候使用。
 @JsonFormat	用于属性或者方法，把属性的格式序列化时转换成指定的格式。示例：@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm") public Date getBirthDate()
 @JsonPropertyOrder	用于类， 和 @JsonProperty 的index属性类似，指定属性在序列化时 json 中的顺序 ， 示例：@JsonPropertyOrder({ "birth_Date", "name" }) public class Person
 @JsonCreator	用于构造方法，和 @JsonProperty 配合使用，适用有参数的构造方法。示例：@JsonCreator public Person(@JsonProperty("name")String name) {…}
 @JsonAnySetter	用于属性或者方法，设置未反序列化的属性名和值作为键值存储到 map 中 @JsonAnySetter public void set(String key, Object value) { map.put(key, value); }
 @JsonAnyGetter	用于方法 ，获取所有未序列化的属性 public Map<String, Object> any() { return map; }
 @JsonNaming	类注解。序列化的时候该注解可将驼峰命名的字段名转换为下划线分隔的小写字母命名方式。反序列化的时候可以将下划线分隔的小写字母转换为驼峰命名的字段名。示例：@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
 @JsonRootName	类注解。需开启mapper.enable(SerializationFeature.WRAP_ROOT_VALUE)，用于序列化时输出带有根属性名称的 JSON 串，形式如 {"root_name":{"id":1,"name":"zhangsan"}}。但不支持该 JSON 串反序列化
 */
// 用于类,指定属性在序列化时 json 中的顺序
@JsonPropertyOrder({"date", "user_name"})
// 批量忽略属性，不进行序列化
@JsonIgnoreProperties(value = {"other"})
// 用于序列化与反序列化时的驼峰命名与小写字母命名转换
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class User {
    @JsonIgnore
    private Map<String, Object> other = new HashMap<>();

    // 正常case
    @JsonProperty("user_name")
    private String userName;
    // 空对象case
    private Integer age;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    // 日期转换case
    private Date date;
    // 默认值case
    private int heione-stop-javat;

    public User() {
    }

    // 反序列化执行构造方法
    @JsonCreator
    public User(@JsonProperty("user_name") String userName) {
        System.out.println("@JsonCreator 注解使得反序列化自动执行该构造方法 " + userName);
        // 反序列化需要手动赋值
        this.userName = userName;
    }

    @JsonAnySetter
    public void set(String key, Object value) {
        other.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> any() {
        return other;
    }
}
