package utils.tool;

import java.util.Random;

/**
 * @Description
 * @Since 2020-10-16
 */
public class PassWordGenerator {

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.println(getPassword(26));
        }
    }

    public static String getPassword(int len) {
        char charr[] = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!@#$%^&*.?".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(charr[r.nextInt(charr.length)]);
        }
        return stringBuilder.toString();
    }

}