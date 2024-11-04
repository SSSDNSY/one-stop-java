package features.jdk21;

/**
 * @Description switch 增强 支持返回值，不用写break
 */
public class SwitchTest {

    public static void main(final String[] args) {
        var unit = "m";
        String str = switch (unit) {
            case "cm" -> "厘米";
            case "m" -> {
                System.out.println("测试多行");
                yield  "米";
            }
            case "mm" -> "毫米";
            case "km" -> "千米";
            case null -> "千米";
            default -> "错误";
        };
        System.out.println(str);
    }

}
