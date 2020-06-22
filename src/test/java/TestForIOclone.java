
import java.io.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @description:
 * @author: pengzh
 * @createDate: 2019/6/11$ 14:52$
 */
public class TestForIOclone {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        //文件对象f1、f2
        File f1 = new File("E:\\desktop\\123\\1.txt");
        File f2 = new File("E:\\desktop\\123\\2.txt");

        //io流的原始对象
        FileInputStream fis = new FileInputStream(f1);
        FileOutputStream fos = new FileOutputStream(f2);

        //io流克隆的对象
        FileInputStream fisClone = null;//new FileInputStream("");
        FileOutputStream fosClone = null;//new FileOutputStream("");

        //克隆对象
//        fisClone = (FileInputStream) BeanUtils.cloneBean(fis);
//        fosClone = (FileOutputStream) BeanUtils.cloneBean(fos);
//
//        if (fisClone != null && fosClone != null) {
//            System.out.println(fis.getClass().getName());
//            System.out.println(fos.getClass().getName());
//        } else return;

        //使用IO流
        int len;
        while ((len = fis.read()) != -1) {
            fos.write(len);
        }

    }
}
