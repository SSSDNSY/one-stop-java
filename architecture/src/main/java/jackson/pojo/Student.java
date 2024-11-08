package jackson.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @desc
 * @class Student
 * @since 2022-02-11
 */
@Data
public class Student {
    // 正常case
    private String name;
    // 日期转换case
    private LocalDateTime date;

}
