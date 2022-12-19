/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sssdnsy.jackson.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author pengzh
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
