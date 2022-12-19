package middleware.jackson.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

/**
 * @author pengzh
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
    private int heione-stop-javat;
    private Date date;

}
