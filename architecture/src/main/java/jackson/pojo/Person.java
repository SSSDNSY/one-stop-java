package jackson.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @desc
 * @class Person
 * @since 2022-02-11
 */
@Data
//@JsonSerialize(using = CustomSerializer.class)
//@JsonDeserialize(using = CustomDeserializer.class)
public class Person {

    private String name;
    private Integer age;
    private int height;
    private Date date;

}
