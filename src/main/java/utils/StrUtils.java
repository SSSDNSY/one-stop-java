package utils;

public class StrUtils {
    public static void main(String[] args) {
        testStrBuild();
        testStr1();
    }
    static void testStr1(){
        String s = "";
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 99999; i++) {
            s +=i;
        }
        System.out.println("testStr1     耗时 "+(System.currentTimeMillis()-t1));
    }
    static void testStrBuild(){
        StringBuilder sb = new StringBuilder();
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 99999; i++) {
            sb.append(i);
        }
        System.out.println("testStrBuild  耗时"+(System.currentTimeMillis()-t1));
    }
}
