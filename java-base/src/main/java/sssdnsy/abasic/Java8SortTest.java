package sssdnsy.abasic;

import lombok.Builder;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @desc
 * @class Java8SortTest
 * @since 2022-08-30
 */
public class Java8SortTest {

    public static void main(String[] args) throws ParseException {
        List<people> list = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

        list.add(people.builder().id(1).birth(formatter.parse("2022-01-30 09:55:43")).marry(formatter.parse("2012-08-30 09:55:43")).build());
        list.add(people.builder().id(2).birth(formatter.parse("2022-02-30 01:55:43")).marry(formatter.parse("2042-08-30 09:55:43")).build());
        list.add(people.builder().id(3).birth(formatter.parse("2012-05-30 09:55:43")).marry(formatter.parse("2015-01-30 09:55:43")).build());
        list.add(people.builder().id(4).birth(formatter.parse("2022-03-30 09:55:43")).marry(formatter.parse("2012-04-30 09:55:43")).build());
        list.add(people.builder().id(5).birth(formatter.parse("2022-10-30 09:55:43")).marry(formatter.parse("2032-05-30 09:55:43")).build());

        list.add(people.builder().id(6).birth(formatter.parse("2025-18-30 09:55:43")).marry(formatter.parse("2012-08-30 00:25:43")).build());
        list.add(people.builder().id(7).birth(formatter.parse("2025-18-30 09:55:43")).marry(formatter.parse("2012-08-30 01:25:43")).build());
        list.add(people.builder().id(8).birth(formatter.parse("2021-18-30 09:55:43")).marry(formatter.parse("2012-08-30 19:25:43")).build());
        list.add(people.builder().id(9).birth(formatter.parse("2026-18-30 09:55:43")).build());
        list.add(people.builder().id(10).marry(formatter.parse("2012-08-30 09:25:43")).build());
        list.add(people.builder().id(11).marry(formatter.parse("2002-08-30 09:25:43")).build());

        list.forEach(System.out::println);
        System.out.println("========================");
        list = list.stream()
                .sorted(Comparator.comparing(people::getBirth, Comparator.nullsLast(Date::compareTo)).reversed()
                .thenComparing(Comparator.comparing(people::getMarry,Comparator.nullsLast(Date::compareTo)).reversed()))
                .collect(Collectors.toList());
        list.forEach(System.out::println);
    }


}

@Data
@Builder
 class people {
    int id;
    Date birth;
    Date marry;
}
