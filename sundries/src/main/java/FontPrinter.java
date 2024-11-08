import java.awt.*;

/**
 * @Desc {@link }
 * @Since 2024-04-17
 */
public class FontPrinter {

    public static void main(String[] args) {
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = e.getAvailableFontFamilyNames();
        for (String name : fontNames) {
            System.out.println(name);
        }
    }

}
