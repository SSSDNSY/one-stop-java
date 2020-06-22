package algorithm.sort;

import sun.java2d.SunGraphics2D;
import sun.java2d.SurfaceData;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;

/**
 * @description: 排序算法演示
 * @author: pengzh
 * @createDate: 2019/6/7$ 21:37$
 */
public class SortGraph extends JPanel {
    static int W = 1280;
    static int H = 720;
    static int x, y, width, height, _x, _y, size = 15;
    static int[] array;
    static Graphics graphics = null;
    public static JPanel jpanel = null;
    public static JMenuBar jMenuBar = null;
    public static JLabel jLabel = new JLabel();

    public SortGraph() {
        super();
        this.setVisible(true);
        this.setBounds(0, H / 20 + 1, W / 101 * 99, H / 20 * 17);
        this.setBorder(new BevelBorder(1));
        this.setBackground(Color.PINK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.gray);
        g.clearRect(0, 0, W, H);
        for (int i = 0; null != array && i < array.length; i++) {
            height = array[i] * _y;
            if(i%2==0){
                g.setColor(Color.cyan);
            }else if(i%5==0){
                g.setColor(Color.MAGENTA);
            }else if(i%3==0){
                g.setColor(Color.ORANGE);
            }else {
                g.setColor(Color.gray);
            }
            g.fillRect(i*_x, 600 - height, width, height + 3);
        }
    }
    /**
    * @description:   展示数组在排序算法中某一时刻的条形图
    * @author:         pengzh
    * @createDate:     2019/6/8 16:44
    */
    public static void showGrp(int[] arr) throws Exception {
        x = y = 0;
        if (array != arr) {
            array = arr;
            _x = (W / 101 * 99)/(arr.length+1);
            width = (W / 101 * 99) / (arr.length * 5 / 4);
            _y = H / 20 * 19 / (arr.length * 10 / 9);
        }
        jpanel.repaint();
    }

    public static void MainFrame() throws Exception {
        final JFrame jf = new JFrame("排序演示");
        //主窗口
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(W, H);
        jf.setLayout(null);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setTitle("排序算法演示");
        jLabel.setBounds(0, 0, 10, 15);
        //菜单栏
        jMenuBar = new JMenuBar();
        JMenu mAlg = new JMenu("算法选择");
        JMenu mAbt = new JMenu("关于");
        JMenuItem jMenuBar_bub = new JMenuItem("冒泡");
        JMenuItem jMenuBar_sel = new JMenuItem("选择");
        JMenuItem jMenuBar_ins = new JMenuItem("插入");
        JMenuItem jMenuBar_quick = new JMenuItem("快速");
        JMenuItem jMenuBar_shell = new JMenuItem("希尔");
        JMenuItem jMenuBar_merge = new JMenuItem("归并");
        JMenuItem jMenuBar_heap = new JMenuItem("堆");
        JMenuItem jMenuBar_radix = new JMenuItem("基数");
        mAlg.add(jMenuBar_bub);
        mAlg.add(jMenuBar_sel);
        mAlg.add(jMenuBar_ins);
        mAlg.add(jMenuBar_quick);
        mAlg.add(jMenuBar_shell);
        mAlg.add(jMenuBar_merge);
        mAlg.add(jMenuBar_heap);
        mAlg.add(jMenuBar_radix);

        jMenuBar.add(mAlg);
        jMenuBar.add(mAbt);
        jMenuBar.setBounds(0, 0, W, H / 20 - 1);
        jMenuBar.setVisible(true);
        jf.setJMenuBar(jMenuBar);

        jpanel = new SortGraph();
        jf.add(jpanel);
        //事件处理

    }



    public static void main(String[] args) throws Exception {
        MainFrame();
        showGrp(new int[]{3, 4, 5, 3, 8});
        Thread.sleep(1000);
        showGrp(new int[]{5, 4, 5, 3, 8});
    }


}
