package features.jdk21;

/**
 * @Description
 * @Since 2024-11-04
 */
public class StringTest {

    public static void main(String[] args) {
        String name = "JDK21";
        String message = STR."Hello \{name}!";
        System.out.println(message);
    }

}
