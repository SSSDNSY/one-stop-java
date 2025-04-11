package util;

import java.util.Arrays;
import java.util.stream.Collectors;

import static cn.hutool.core.text.StrPool.*;

/**
 * @author pengzh
 * @desc 打印输出工具
 * @since 2025-04-11
 */
public final class GeneralUtil {

    private GeneralUtil() {
    }

    public static void printArray(Object[] arr) {
        System.out.println("打印数组：");
        System.out.println(Arrays.stream(arr).map(Object::toString)
                .collect(Collectors.joining(COMMA, BRACKET_START, BRACKET_END)));
    }

    public static void printArray(int[] arr) {
        System.out.println("打印数组：");
        System.out.println(Arrays.stream(arr).mapToObj(String::valueOf)
                .collect(Collectors.joining(COMMA, BRACKET_START, BRACKET_END)));
    }

}
