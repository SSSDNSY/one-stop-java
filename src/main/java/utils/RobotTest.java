package utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;

/**
 * @author pengzh
 * @since 2020-05-26
 */

@Slf4j
public class RobotTest {
    @Test
    public void Test4R() throws Exception {
        final Robot robot = new Robot();
//        robot.keyPress(73);
        final BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(1, 1, 433, 200));
        ImageIO.write(screenCapture, "gif", new File("1.gif"));
        final Color pixelColor = robot.getPixelColor(1, 2);
        //BUTTON1_MASK 1 2 3 左中右
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(300);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON2_MASK);
        robot.delay(300);
        robot.mouseRelease(InputEvent.BUTTON2_MASK);
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.delay(300);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        log.debug("color{}", pixelColor);
        robot.delay(1000);
        robot.mouseWheel(12);
    }
}
