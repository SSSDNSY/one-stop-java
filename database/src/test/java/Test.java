import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Auther: imi
 * @Date: 2019/4/27 14:59
 * @Description:
 */
public class Test {


    private static Test TEST = null;


    private void doSth() {
        System.out.println("private method print the name " + this.name);
    }

    private String name;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public static synchronized Test getTest() {
        if (TEST == null) {
            TEST = new Test();
        }
        return TEST;
    }

    /**
     * [B4, A1, A3, A2, B3, B1]
     * [A1, A2, A3, B1, B3, B4]
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        testBigDecimal();
    }

    public static void testBigDecimal() {
        BigDecimal addAppend = new BigDecimal(2d).add(new BigDecimal(1d)).add(new BigDecimal(8d));
        double score = addAppend.divide(new BigDecimal(2d), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(score);
    }

    @Data
    @AllArgsConstructor
    static
    class Obj {
        //        private String l1;
//        private String l2;
        private String id;
    }

    static void a(int a, Integer b) {
        b = 3;
        System.out.println(a + " " + b);
    }

    public static void startTomcat() throws IOException {
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k  W:/server/nginx_proxy_tomcat/tomcat_8222/bin/startup.bat");
    }

    public static void stopTomcat() throws IOException {
        Runtime.getRuntime().exec("W:/server/nginx_proxy_tomcat/tomcat_8222/bin/showdown.bat");
    }

    public void stringFormatTest() {
        String updSql = " update %s set %s='%s' where id='%d' ";
        updSql = String.format(updSql, "op_dj_base", "atttachemt10", "http//asdfzxc.comasdf.co", 123);
        System.out.printf(updSql);
    }

    public static boolean containsChinese(String Str) {
        Pattern p = Pattern.compile("^.*([\\u4E00-\\uFA29]|[\\uE7C7-\\uE7F3])+.*$");
        Matcher m = p.matcher(Str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public void checkContainsChinese() {
//        Assert.assertTrue(containsChinese("䒦"));//error
//        Assert.assertTrue(containsChinese("发送到"));
//        Assert.assertFalse(containsChinese("ff000"));
//        Assert.assertFalse(containsChinese("fwefwefwef//.,431234#$#$#/.,"));
        assertTrue(containChinese("asdf䒦afd"));
        assertTrue(containChinese("发送到234"));
        assertFalse(containChinese("ff0f00"));
        assertFalse(containChinese("fwefwefwef//.,431234#$#$#/.,"));
    }

    public boolean containChinese(char c) {
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        if (sc == Character.UnicodeScript.HAN) {
            return true;
        }
        return false;
    }

    public boolean containChinese(String str) {
        for (char c : str.toCharArray()) {
            if (containChinese(c)) {
                return true;
            }
        }
        return false;
    }

    public void TestExtends() {
//        Assert.assertTrue(new C() instanceof Object);

        String str = "asdfa";
        String str1 = "asdfa,2341af";
        String[] split = str1.split(",");
        List<Long> l1 = new ArrayList<>();
        l1.add(1L);
        l1.add(2L);
        l1.add(3L);
        List<Long> l2 = new ArrayList<>();
        l2.add(1L);
        l2.add(2L);
        List<Long> longs = subList(l1, l2);
        System.out.printf(longs.toString());
    }

    public static <T> List<T> subList(List<T> list1, List<T> list2) {
        Map<T, T> tempMap = list2.parallelStream().collect(Collectors.toMap(Function.identity(), Function.identity(), (oldData, newData) -> newData));
        return list1.parallelStream().filter(str -> {
            return !tempMap.containsKey(str);
        }).collect(Collectors.toList());
    }


}
