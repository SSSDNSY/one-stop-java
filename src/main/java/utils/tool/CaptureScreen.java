package utils.tool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

/**
 * @desc
 * @class CaptureScreen
 * @since 2022-10-11
 */
public class CaptureScreen {

    public  static  void captureScreen(String fileName) throws Exception {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Rectangle screenRectangle = new Rectangle(screenSize);

        Robot robot = new Robot();

        BufferedImage image = robot.createScreenCapture(screenRectangle);

        ImageIO.write(image, ".png", new File(fileName));

    }

    public static void main(String[] args) throws Exception {
        CaptureScreen.captureScreen("屏幕截图");
    }

}
