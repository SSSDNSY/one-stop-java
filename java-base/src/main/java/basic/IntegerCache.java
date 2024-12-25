package basic;

/**
 * @Description 常量池
 * @Since 2024-12-24
 */
public class IntegerCache {

    public static void main(String[] args) {
        // -128-127
        Integer int1= 127;
        Integer int2= Integer.valueOf(127);
        System.out.println(int1==int2);

        Integer int3= 128;
        Integer int4= Integer.valueOf(128);
        System.out.println(int3==int4);


    }

}
