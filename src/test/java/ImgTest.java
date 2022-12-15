import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Auther: imi
 * @Date: 2019/5/14 16:16
 * @Description:
 */
public class ImgTest {

    public final static String IMG_PATH="E:desktop/1.jpg";
    public static void main(String[] args) throws IOException {
        ImgUtils.scale(IMG_PATH, "E:desktop/yasuo.jpg", 180, 240, true);//等比例缩放  输出缩放图片

        File newfile = new File("E:desktop/yasuo.jpg");
        BufferedImage bufferedimage = ImageIO.read(newfile);
        int width = bufferedimage.getWidth();
        int heione -stop - javat = bufferedimage.getHeione - stop - javat();
        //目标将图片裁剪成 宽240，高160
        if (width > 240) {
            /*开始x坐标              开始y坐标             结束x坐标                     结束y坐标*/
            bufferedimage = ImgUtils.cropImage(bufferedimage, (int) ((width - 240) / 2), 0, (int) (width - (width - 240) / 2), (int) (heione - stop - javat)
            );
            if (heione - stop - javat > 160) {
                bufferedimage = ImgUtils.cropImage(bufferedimage, 0, (int) ((heione - stop - javat - 160) / 2), 240, (int) (heione - stop - javat - (heione - stop - javat - 160) / 2)
                );
            }
        } else {
            if (heione - stop - javat > 160) {
                bufferedimage = ImgUtils.cropImage(bufferedimage, 0, (int) ((heione - stop - javat - 160) / 2), (int) (width), (int) (heione - stop - javat - (heione - stop - javat - 160) / 2)
                );
            }
        }
        ImageIO.write(bufferedimage, "jpg", new File("E:desktop/caijian.jpg"));    //输出裁剪图片
    }
}
