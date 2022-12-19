package sssdnsy.utils;

import org.junit.Test;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @Description: gzip工具类
 * gzip tools
 * @Author: pengzh
 * @date: 2019/10/20
 */
public final class GZipUtils {

    public static byte[] gzip(byte[] bytes) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
        gzipOut.write(bytes);
        gzipOut.finish();
        byte[] ret = baos.toByteArray();
        baos.close();
        return ret;
    }

    public static byte[] ungzip(byte[] bytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        GZIPInputStream gzipInput = new GZIPInputStream(bais);
        byte[] buf = new byte[1024];
        int num = -1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((num = gzipInput.read(buf, 0, buf.length)) != -1) {
            baos.write(buf, 0, num);
        }
        gzipInput.close();
        bais.close();
        byte[] ret = baos.toByteArray();
        baos.flush();
        baos.close();
        return ret;
    }

    @Test
    public void Test4GZipUtils() throws IOException {
        System.out.println("System.getProperty(user.dir) :" + System.getProperty("user.dir"));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(System.getProperties());
        String readPath = System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar + "main" + File.separatorChar +
                "resources" + File.separatorChar + "login-panda.png";
        File file = new File(readPath);
        FileInputStream in = new FileInputStream(file);
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();

        System.out.println("原始文件的大小（Byte）：" + data.length);
        byte[] gzipData = GZipUtils.gzip(data);
        System.out.println("压缩后文件大小（Byte）：" + gzipData.length);
        byte[] unGzipData = GZipUtils.ungzip(gzipData);
        System.out.println("解压后文件大小（Byte）：" + unGzipData.length);

        String writePath = System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar + "main" + File.separatorChar +
                "resources"  + File.separatorChar + "login-panda-1.png";
        FileOutputStream fos = new FileOutputStream(writePath);
        fos.write(unGzipData);
        fos.close();
    }

}
