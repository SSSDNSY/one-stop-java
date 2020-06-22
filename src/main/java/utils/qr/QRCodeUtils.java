package utils.qr;

import cn.hutool.core.io.FileUtil;
import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 二维码工具类
 *
 * @author pengzh
 * @since 2020-05-27
 */
@Slf4j
public class QRCodeUtils {

    public static final String UTF_8 = "UTF8";

    public static void QrEncode(String text, File desFile) throws Exception {
        Qrcode qrcode = new Qrcode();
        qrcode.setQrcodeVersion(7);
        qrcode.setQrcodeEncodeMode('B');
//        纠错级别（L 7%、M 15%、Q 25%、H 30%）和版本有关
        qrcode.setQrcodeErrorCorrect('M');

        BufferedImage bufferedImage = new BufferedImage(139, 139, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.BLACK);
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, 139, 139);

        final byte[] bytes = text.getBytes(UTF_8);
        if (bytes.length > 0 && bytes.length < 128) {
            final boolean[][] calQrcode = qrcode.calQrcode(bytes);
            for (int i = 0; i < calQrcode.length; i++) {
                for (int j = 0; j < calQrcode.length; j++) {
                    if (calQrcode[j][i]) {
                        graphics.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
                    }
                }
            }
        } else {
            throw new Exception("Code Text to long,the length need <128");
        }
        graphics.dispose();
        bufferedImage.flush();
        ImageIO.write(bufferedImage, "png", desFile);
    }

    public static String QrDecode(File imageFile) throws Exception {
        final BufferedImage bufferedImage = ImageIO.read(imageFile);
        final QRCodeDecoder qrCodeDecoder = new QRCodeDecoder();
        final QRCodeImageImpl qrCodeImage = new QRCodeImageImpl(bufferedImage);
        final byte[] decode = qrCodeDecoder.decode(qrCodeImage);
        return new String(decode, "UTF8");
    }

    static class QRCodeImageImpl implements QRCodeImage {

        private BufferedImage image;

        public QRCodeImageImpl(BufferedImage image) {
            this.image = image;
        }

        @Override
        public int getWidth() {
            return this.image.getWidth();
        }

        @Override
        public int getHeight() {
            return this.image.getHeight();
        }

        @Override
        public int getPixel(int x, int y) {
            return this.image.getRGB(x, y);
        }
    }

    public static void main(String[] args) throws Exception {
        final String fileName = "qrCode.png";
        File f = new File(fileName);
        QRCodeUtils.QrEncode("这就是二维码的%&%&*DKOK/*S类&**容:732390113@qq.com", f);
        log.debug("\n是否生成图片: {}\n", FileUtil.exist(fileName));
        final String s = QRCodeUtils.QrDecode(f);
        log.debug("\n读取二维码类容:{}\n", s);

    }
}
