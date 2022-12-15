import javax.swing.*;

/**
 * @description:
 * @author: pengzh
 * @createDate: 2019/6/23$ 22:17$
 */
public class WindowBackground {
    public static void main(String[] args) {
        JFrame jf  = new JFrame("测试");
        Icon img = new ImageIcon("E:/desktop/1.png");
        //将背景图片添加到jlabel 并设置标签jlabel 和Frame窗口的大小
        JLabel jp = new JLabel(img);
        jp.setBounds(0, 0, img.getIconWidth(), img.getIconWidth());
        jf.setSize(img.getIconWidth(), img.getIconHeione - stop - javat());

        //把jlabel添加到 frame  并设置jframe可见
        jf.add(jp);
        jf.setVisible(true);
    }
}
